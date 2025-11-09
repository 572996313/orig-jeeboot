#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Base-Core批量类迁移工具

功能：
1. 批量迁移多个类到目标模块
2. 自动更新所有引用
3. 自动编译验证
4. 生成批量迁移报告
"""

import os
import subprocess
import json
from datetime import datetime

class BatchMigrator:
    def __init__(self, dry_run=False):
        self.dry_run = dry_run
        self.results = []
        self.total_classes = 0
        self.success_count = 0
        self.failed_count = 0
        
    def run_command(self, cmd):
        """执行命令"""
        try:
            result = subprocess.run(cmd, shell=True, capture_output=True, text=True, encoding='utf-8', errors='ignore')
            return result.returncode == 0, result.stdout, result.stderr
        except Exception as e:
            return False, "", str(e)
    
    def migrate_single_class(self, class_info):
        """迁移单个类"""
        class_name = class_info['name']
        target_module = class_info['target']
        subpackage = class_info.get('subpackage', '')
        
        print(f"\n{'='*60}")
        print(f"迁移类 {self.success_count + 1}/{self.total_classes}: {class_name}")
        print(f"{'='*60}\n")
        
        result = {
            'class_name': class_name,
            'target_module': target_module,
            'subpackage': subpackage,
            'success': False,
            'steps': {}
        }
        
        # 步骤1：迁移类文件
        print(f"📦 步骤1: 迁移类文件...")
        cmd = f'python migrate-base-core-class.py --class {class_name} --target {target_module}'
        if subpackage:
            cmd += f' --subpackage {subpackage}'
        if self.dry_run:
            cmd += ' --dry-run'
        
        success, stdout, stderr = self.run_command(cmd)
        result['steps']['migrate'] = {
            'success': success,
            'output': stdout[:200] if stdout else stderr[:200]
        }
        
        if not success:
            print(f"  ❌ 迁移失败: {stderr[:100]}")
            result['error'] = 'Migration failed'
            self.failed_count += 1
            return result
        
        print(f"  ✅ 迁移成功")
        
        # 从输出中提取包名信息
        old_package = None
        new_package = None
        for line in stdout.split('\n'):
            if '原包名:' in line or 'old package:' in line.lower():
                old_package = line.split(':')[-1].strip()
            if '新包名:' in line or 'new package:' in line.lower():
                new_package = line.split(':')[-1].strip()
        
        if not old_package or not new_package:
            print(f"  ⚠️  无法提取包名信息，跳过引用更新")
            result['warning'] = 'Package names not found'
            self.success_count += 1
            result['success'] = True
            return result
        
        # 步骤2：更新引用
        if not self.dry_run:
            print(f"🔄 步骤2: 更新类引用...")
            cmd = f'python update-class-references.py --class {class_name} --old-package {old_package} --new-package {new_package}'
            
            success, stdout, stderr = self.run_command(cmd)
            result['steps']['update_refs'] = {
                'success': success,
                'output': stdout[:200] if stdout else stderr[:200]
            }
            
            if success:
                # 提取更新的文件数
                for line in stdout.split('\n'):
                    if '更新文件数:' in line or 'updated' in line.lower():
                        try:
                            count = int(''.join(filter(str.isdigit, line)))
                            result['refs_updated'] = count
                        except:
                            pass
                print(f"  ✅ 引用更新完成")
            else:
                print(f"  ⚠️  引用更新失败，但类已迁移")
        
        result['success'] = True
        result['old_package'] = old_package
        result['new_package'] = new_package
        self.success_count += 1
        
        return result
    
    def compile_verify(self, module=None):
        """编译验证"""
        print(f"\n{'='*60}")
        print(f"🔨 编译验证...")
        print(f"{'='*60}\n")
        
        if module:
            cmd = f'mvn clean compile -pl {module} -am'
        else:
            cmd = 'mvn clean compile'
        
        success, stdout, stderr = self.run_command(cmd)
        
        if success or 'BUILD SUCCESS' in stdout:
            print(f"✅ 编译成功")
            return True
        else:
            print(f"❌ 编译失败")
            print(f"错误信息: {stderr[:500]}")
            return False
    
    def migrate_batch(self, classes_config):
        """批量迁移类"""
        self.total_classes = len(classes_config)
        
        print(f"\n{'='*60}")
        print(f"Base-Core批量类迁移工具")
        print(f"{'='*60}\n")
        
        if self.dry_run:
            print(f"⚠️  试运行模式 - 不会实际修改文件\n")
        
        print(f"计划迁移 {self.total_classes} 个类\n")
        
        # 逐个迁移
        for class_info in classes_config:
            result = self.migrate_single_class(class_info)
            self.results.append(result)
            
            # 每迁移5个类验证一次编译
            if not self.dry_run and (self.success_count % 5 == 0):
                if not self.compile_verify(result['target_module']):
                    print(f"\n⚠️  编译失败，建议停止并检查问题")
                    break
        
        # 生成报告
        self.generate_report()
        
        return self.success_count, self.failed_count
    
    def generate_report(self):
        """生成批量迁移报告"""
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        report_file = f"batch-migration-report-{timestamp}.md"
        
        with open(report_file, 'w', encoding='utf-8') as f:
            f.write(f"# Base-Core批量迁移报告\n\n")
            f.write(f"## 📊 迁移摘要\n\n")
            f.write(f"- **迁移时间**: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n")
            f.write(f"- **计划迁移**: {self.total_classes}个类\n")
            f.write(f"- **成功迁移**: {self.success_count}个\n")
            f.write(f"- **失败**: {self.failed_count}个\n")
            f.write(f"- **成功率**: {self.success_count/self.total_classes*100:.1f}%\n")
            f.write(f"- **试运行**: {'是' if self.dry_run else '否'}\n\n")
            
            f.write(f"## 📋 迁移详情\n\n")
            f.write(f"| # | 类名 | 目标模块 | 状态 | 旧包名 | 新包名 |\n")
            f.write(f"|---|------|----------|------|--------|--------|\n")
            
            for i, result in enumerate(self.results, 1):
                status = "✅" if result['success'] else "❌"
                old_pkg = result.get('old_package', '-')
                new_pkg = result.get('new_package', '-')
                f.write(f"| {i} | {result['class_name']} | {result['target_module']} | {status} | `{old_pkg}` | `{new_pkg}` |\n")
            
            f.write(f"\n## 🔍 详细步骤\n\n")
            for i, result in enumerate(self.results, 1):
                f.write(f"### {i}. {result['class_name']}\n\n")
                f.write(f"- **目标模块**: {result['target_module']}\n")
                f.write(f"- **子包**: {result.get('subpackage', '-')}\n")
                f.write(f"- **状态**: {'✅ 成功' if result['success'] else '❌ 失败'}\n")
                
                if result.get('old_package'):
                    f.write(f"- **旧包名**: `{result['old_package']}`\n")
                    f.write(f"- **新包名**: `{result['new_package']}`\n")
                
                if result.get('refs_updated'):
                    f.write(f"- **引用更新**: {result['refs_updated']}个文件\n")
                
                if result.get('error'):
                    f.write(f"- **错误**: {result['error']}\n")
                
                f.write(f"\n")
        
        print(f"\n📄 报告已保存: {report_file}")

def main():
    import argparse
    
    parser = argparse.ArgumentParser(description='Base-Core批量类迁移工具')
    parser.add_argument('--config', required=True, help='迁移配置文件(JSON)')
    parser.add_argument('--dry-run', action='store_true', help='试运行模式')
    
    args = parser.parse_args()
    
    # 读取配置
    try:
        with open(args.config, 'r', encoding='utf-8') as f:
            config = json.load(f)
            classes = config.get('classes', [])
    except Exception as e:
        print(f"❌ 读取配置文件失败: {e}")
        return 1
    
    if not classes:
        print(f"❌ 配置文件中没有要迁移的类")
        return 1
    
    # 执行批量迁移
    migrator = BatchMigrator(dry_run=args.dry_run)
    success, failed = migrator.migrate_batch(classes)
    
    print(f"\n{'='*60}")
    print(f"✨ 批量迁移完成")
    print(f"{'='*60}")
    print(f"  成功: {success}个")
    print(f"  失败: {failed}个")
    print(f"{'='*60}\n")
    
    return 0 if failed == 0 else 1

if __name__ == "__main__":
    exit(main())