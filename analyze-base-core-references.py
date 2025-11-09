#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Base-Core类引用分析工具

功能：
1. 扫描base-core模块中的所有Java类
2. 分析每个类在其他模块中的引用情况
3. 生成类引用关系图
4. 识别迁移优先级
"""

import os
import re
import json
from pathlib import Path
from collections import defaultdict

class BaseCoreClas进Analyzer:
    def __init__(self):
        self.base_core_dir = "jeecg-boot-base-core/src/main/java"
        self.project_root = "."
        self.classes = {}  # {class_name: class_info}
        self.references = defaultdict(list)  # {class_name: [reference_locations]}
        
    def scan_base_core_classes(self):
        """扫描base-core模块中的所有类"""
        print("🔍 扫描base-core模块中的类...")
        
        if not os.path.exists(self.base_core_dir):
            print(f"❌ base-core目录不存在: {self.base_core_dir}")
            return
        
        for root, dirs, files in os.walk(self.base_core_dir):
            for file in files:
                if file.endswith('.java'):
                    file_path = os.path.join(root, file)
                    self.analyze_class_file(file_path)
        
        print(f"✅ 找到 {len(self.classes)} 个类")
    
    def analyze_class_file(self, file_path):
        """分析单个类文件"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取包名
            package_match = re.search(r'package\s+([\w.]+);', content)
            package_name = package_match.group(1) if package_match else ""
            
            # 提取类名
            class_match = re.search(r'(?:public\s+)?(?:class|interface|enum)\s+(\w+)', content)
            if not class_match:
                return
            
            class_name = class_match.group(1)
            full_class_name = f"{package_name}.{class_name}"
            
            # 获取相对路径
            rel_path = os.path.relpath(file_path, self.base_core_dir)
            
            # 判断类型
            class_type = "class"
            if "interface " in content:
                class_type = "interface"
            elif "enum " in content:
                class_type = "enum"
            elif "@interface" in content:
                class_type = "annotation"
            
            # 判断类别
            category = self.categorize_class(package_name, class_name, content)
            
            self.classes[class_name] = {
                "name": class_name,
                "full_name": full_class_name,
                "package": package_name,
                "path": file_path,
                "rel_path": rel_path,
                "type": class_type,
                "category": category,
                "line_count": content.count('\n') + 1
            }
            
        except Exception as e:
            print(f"  ⚠️  分析文件失败 {file_path}: {e}")
    
    def categorize_class(self, package, class_name, content):
        """根据包名和类名判断类别"""
        package_lower = package.lower()
        class_lower = class_name.lower()
        
        # VO类
        if 'vo' in package_lower or class_lower.endswith('vo') or 'model' in class_lower:
            return "VO"
        
        # Controller基类
        if 'controller' in class_lower or 'BaseController' in class_name:
            return "Controller"
        
        # Entity基类
        if 'entity' in class_lower or class_name in ['JeecgEntity', 'BaseEntity']:
            return "Entity"
        
        # 工具类
        if 'util' in package_lower or class_lower.endswith('util') or class_lower.endswith('utils') or class_lower.endswith('helper'):
            return "Util"
        
        # 常量类
        if 'constant' in package_lower or class_lower.endswith('constant') or class_lower.endswith('constants'):
            return "Constant"
        
        # 注解
        if '@interface' in content or 'annotation' in package_lower:
            return "Annotation"
        
        # 配置类
        if 'config' in package_lower or class_lower.endswith('config') or class_lower.endswith('configuration'):
            return "Config"
        
        # 异常类
        if 'exception' in package_lower or class_lower.endswith('exception'):
            return "Exception"
        
        # API/接口
        if 'api' in package_lower or class_name.startswith('I'):
            return "API"
        
        return "Other"
    
    def scan_references(self):
        """扫描其他模块对base-core类的引用"""
        print("\n🔍 扫描类引用...")
        
        exclude_dirs = {
            'jeecg-boot-base-core',
            'target',
            'node_modules',
            '.git',
            '.idea',
            'base-core-migration-backup'
        }
        
        java_files = []
        for root, dirs, files in os.walk(self.project_root):
            # 过滤排除目录
            dirs[:] = [d for d in dirs if d not in exclude_dirs]
            
            for file in files:
                if file.endswith('.java'):
                    java_files.append(os.path.join(root, file))
        
        print(f"  找到 {len(java_files)} 个Java文件")
        
        for java_file in java_files:
            self.analyze_references_in_file(java_file)
        
        # 统计引用
        ref_count = sum(len(refs) for refs in self.references.values())
        print(f"✅ 找到 {ref_count} 处引用")
    
    def analyze_references_in_file(self, file_path):
        """分析文件中对base-core类的引用"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            for class_name, class_info in self.classes.items():
                # 检查import语句
                import_pattern = f"import\\s+{re.escape(class_info['full_name'])};"
                if re.search(import_pattern, content):
                    self.references[class_name].append({
                        "file": file_path,
                        "type": "import",
                        "full_class": class_info['full_name']
                    })
                
                # 检查直接使用（简单匹配）
                if class_name in content and class_name not in file_path:
                    # 计算使用次数
                    usage_count = content.count(class_name)
                    if usage_count > 0:
                        existing = [r for r in self.references[class_name] if r['file'] == file_path]
                        if not existing:
                            self.references[class_name].append({
                                "file": file_path,
                                "type": "usage",
                                "count": usage_count
                            })
        
        except Exception as e:
            pass  # 忽略读取错误
    
    def calculate_migration_priority(self):
        """计算迁移优先级"""
        print("\n📊 计算迁移优先级...")
        
        for class_name, class_info in self.classes.items():
            refs = self.references.get(class_name, [])
            ref_count = len(refs)
            
            # 优先级计算规则
            priority = 0
            
            # 1. 被引用次数少的优先（叶子节点）
            if ref_count == 0:
                priority = 10
            elif ref_count <= 5:
                priority = 8
            elif ref_count <= 20:
                priority = 5
            else:
                priority = 2
            
            # 2. VO类优先
            if class_info['category'] == 'VO':
                priority += 3
            
            # 3. 工具类和常量类优先
            if class_info['category'] in ['Util', 'Constant']:
                priority += 2
            
            # 4. 注解类优先
            if class_info['category'] == 'Annotation':
                priority += 2
            
            # 5. 关键类降低优先级（需要更谨慎）
            key_classes = ['LoginUser', 'JeecgController', 'JeecgEntity', 'RedisUtil']
            if class_name in key_classes:
                priority = max(1, priority - 3)
            
            class_info['priority'] = priority
            class_info['reference_count'] = ref_count
            class_info['references'] = refs
    
    def generate_report(self):
        """生成分析报告"""
        print("\n📝 生成分析报告...")
        
        # 按优先级排序
        sorted_classes = sorted(
            self.classes.items(),
            key=lambda x: (-x[1]['priority'], x[1]['reference_count'])
        )
        
        report = {
            "summary": {
                "total_classes": len(self.classes),
                "total_references": sum(len(refs) for refs in self.references.values()),
                "categories": self.get_category_stats(),
                "timestamp": self.get_timestamp()
            },
            "classes": {},
            "migration_order": []
        }
        
        for class_name, class_info in sorted_classes:
            report["classes"][class_name] = class_info
            report["migration_order"].append({
                "class": class_name,
                "priority": class_info['priority'],
                "category": class_info['category'],
                "references": class_info['reference_count'],
                "target_module": self.suggest_target_module(class_info)
            })
        
        # 保存JSON报告
        json_file = "base-core-class-references.json"
        with open(json_file, 'w', encoding='utf-8') as f:
            json.dump(report, f, indent=2, ensure_ascii=False)
        print(f"✅ JSON报告已保存: {json_file}")
        
        # 生成Markdown报告
        self.generate_markdown_report(report)
    
    def get_category_stats(self):
        """获取类别统计"""
        stats = defaultdict(int)
        for class_info in self.classes.values():
            stats[class_info['category']] += 1
        return dict(stats)
    
    def get_timestamp(self):
        """获取时间戳"""
        from datetime import datetime
        return datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    
    def suggest_target_module(self, class_info):
        """建议目标模块"""
        category = class_info['category']
        
        if category in ['VO', 'API', 'Annotation']:
            return "jeecg-boot-base-api"
        elif category in ['Util']:
            return "jeecg-boot-base-utils"
        elif category in ['Constant']:
            return "jeecg-boot-base-constants"
        elif category in ['Controller', 'Entity', 'Config']:
            return "jeecg-boot-base-core-lite"
        else:
            return "jeecg-boot-base-core-lite"  # 默认
    
    def generate_markdown_report(self, report):
        """生成Markdown格式报告"""
        md_file = "base-core-class-references-report.md"
        
        with open(md_file, 'w', encoding='utf-8') as f:
            f.write("# Base-Core类引用分析报告\n\n")
            
            # 摘要
            f.write("## 📊 摘要\n\n")
            summary = report['summary']
            f.write(f"- **分析时间**: {summary['timestamp']}\n")
            f.write(f"- **类总数**: {summary['total_classes']}\n")
            f.write(f"- **引用总数**: {summary['total_references']}\n")
            f.write(f"- **平均引用**: {summary['total_references'] / max(summary['total_classes'], 1):.1f} 次/类\n\n")
            
            # 类别统计
            f.write("## 📦 类别统计\n\n")
            f.write("| 类别 | 数量 | 百分比 |\n")
            f.write("|------|------|--------|\n")
            for category, count in sorted(summary['categories'].items(), key=lambda x: -x[1]):
                percentage = count / summary['total_classes'] * 100
                f.write(f"| {category} | {count} | {percentage:.1f}% |\n")
            f.write("\n")
            
            # 迁移顺序（前30个）
            f.write("## 🎯 建议迁移顺序（Top 30）\n\n")
            f.write("| 优先级 | 类名 | 类别 | 引用次数 | 目标模块 |\n")
            f.write("|--------|------|------|----------|----------|\n")
            for i, item in enumerate(report['migration_order'][:30], 1):
                priority_icon = "🔴" if item['priority'] >= 8 else "🟡" if item['priority'] >= 5 else "🟢"
                f.write(f"| {priority_icon} {item['priority']} | `{item['class']}` | {item['category']} | {item['references']} | {item['target_module']} |\n")
            f.write("\n")
            
            # 按类别分组
            f.write("## 📋 按类别分组\n\n")
            category_groups = defaultdict(list)
            for class_name, class_info in report['classes'].items():
                category_groups[class_info['category']].append((class_name, class_info))
            
            for category in sorted(category_groups.keys()):
                classes = category_groups[category]
                f.write(f"### {category} ({len(classes)}个)\n\n")
                f.write("| 类名 | 引用次数 | 优先级 | 目标模块 |\n")
                f.write("|------|----------|--------|----------|\n")
                for class_name, class_info in sorted(classes, key=lambda x: -x[1]['priority']):
                    target = self.suggest_target_module(class_info)
                    f.write(f"| `{class_name}` | {class_info['reference_count']} | {class_info['priority']} | {target} |\n")
                f.write("\n")
            
            # 高优先级类详情
            f.write("## 🔥 高优先级类详情\n\n")
            high_priority = [item for item in report['migration_order'] if item['priority'] >= 8]
            for item in high_priority[:10]:
                class_info = report['classes'][item['class']]
                f.write(f"### {item['class']}\n\n")
                f.write(f"- **完整类名**: `{class_info['full_name']}`\n")
                f.write(f"- **类别**: {class_info['category']}\n")
                f.write(f"- **优先级**: {item['priority']}\n")
                f.write(f"- **引用次数**: {item['references']}\n")
                f.write(f"- **目标模块**: {item['target_module']}\n")
                f.write(f"- **文件路径**: `{class_info['rel_path']}`\n")
                f.write(f"- **代码行数**: {class_info['line_count']}\n")
                
                if class_info['references']:
                    f.write(f"\n**引用位置**:\n")
                    for ref in class_info['references'][:5]:  # 只显示前5个
                        f.write(f"- `{ref['file']}`\n")
                    if len(class_info['references']) > 5:
                        f.write(f"- ... 还有 {len(class_info['references']) - 5} 处引用\n")
                f.write("\n")
        
        print(f"✅ Markdown报告已保存: {md_file}")
    
    def run(self):
        """执行分析"""
        self.scan_base_core_classes()
        self.scan_references()
        self.calculate_migration_priority()
        self.generate_report()

if __name__ == "__main__":
    print("=" * 60)
    print("Base-Core类引用分析工具")
    print("=" * 60)
    print()
    
    analyzer = BaseCoreClas进Analyzer()
    analyzer.run()
    
    print()
    print("=" * 60)
    print("🎉 分析完成！")
    print("=" * 60)
    print("\n查看报告:")
    print("  - JSON格式: base-core-class-references.json")
    print("  - Markdown格式: base-core-class-references-report.md")