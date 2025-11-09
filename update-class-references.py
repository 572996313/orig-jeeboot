#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
类引用更新工具

功能：
1. 自动更新项目中对已迁移类的import语句
2. 更新包名引用
3. 生成更新报告
"""

import os
import re
import argparse
from pathlib import Path

class ReferenceUpdater:
    def __init__(self, class_name, old_package, new_package, dry_run=False):
        self.class_name = class_name
        self.old_package = old_package
        self.new_package = new_package
        self.dry_run = dry_run
        
        self.old_import = f"import {old_package}.{class_name};"
        self.new_import = f"import {new_package}.{class_name};"
        
        self.updated_files = []
        self.total_replacements = 0
        
    def scan_and_update(self):
        """扫描并更新所有Java文件"""
        print(f"🔍 扫描Java文件...")
        print(f"  旧包名: {self.old_package}")
        print(f"  新包名: {self.new_package}")
        print()
        
        exclude_dirs = {
            'target',
            'node_modules',
            '.git',
            '.idea',
            'base-core-migration-backup'
        }
        
        java_files = []
        for root, dirs, files in os.walk('.'):
            # 过滤排除目录
            dirs[:] = [d for d in dirs if d not in exclude_dirs]
            
            for file in files:
                if file.endswith('.java'):
                    java_files.append(os.path.join(root, file))
        
        print(f"✅ 找到 {len(java_files)} 个Java文件\n")
        
        for java_file in java_files:
            self.update_file(java_file)
        
        return len(self.updated_files) > 0
    
    def update_file(self, file_path):
        """更新单个文件"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 检查是否包含旧的import
            if self.old_import not in content:
                return
            
            # 替换import语句
            new_content = content.replace(self.old_import, self.new_import)
            
            # 计算替换次数
            replacements = content.count(self.old_import)
            
            if not self.dry_run:
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.write(new_content)
                print(f"  ✅ 更新: {file_path} ({replacements}处)")
            else:
                print(f"  ✅ [DRY-RUN] 将更新: {file_path} ({replacements}处)")
            
            self.updated_files.append({
                'file': file_path,
                'replacements': replacements
            })
            self.total_replacements += replacements
            
        except Exception as e:
            print(f"  ⚠️  处理文件失败 {file_path}: {e}")
    
    def generate_report(self):
        """生成更新报告"""
        if not self.updated_files:
            print("\n📊 未找到需要更新的文件")
            return
        
        print(f"\n📊 更新报告")
        print(f"{'='*60}")
        print(f"类名: {self.class_name}")
        print(f"旧包名: {self.old_package}")
        print(f"新包名: {self.new_package}")
        print(f"更新文件数: {len(self.updated_files)}")
        print(f"总替换次数: {self.total_replacements}")
        print(f"{'='*60}\n")
        
        # 写入报告文件
        report_file = f"update-references-{self.class_name}.md"
        
        if not self.dry_run:
            with open(report_file, 'w', encoding='utf-8') as f:
                f.write(f"# {self.class_name} 引用更新报告\n\n")
                f.write(f"## 更新信息\n\n")
                f.write(f"- **类名**: `{self.class_name}`\n")
                f.write(f"- **旧包名**: `{self.old_package}`\n")
                f.write(f"- **新包名**: `{self.new_package}`\n")
                f.write(f"- **更新文件数**: {len(self.updated_files)}\n")
                f.write(f"- **总替换次数**: {self.total_replacements}\n\n")
                
                f.write(f"## 更新文件列表\n\n")
                f.write("| 文件 | 替换次数 |\n")
                f.write("|------|----------|\n")
                
                for item in sorted(self.updated_files, key=lambda x: -x['replacements']):
                    f.write(f"| `{item['file']}` | {item['replacements']} |\n")
                
                f.write(f"\n## 验证步骤\n\n")
                f.write(f"```bash\n")
                f.write(f"# 1. 编译检查\n")
                f.write(f"mvn clean compile\n\n")
                f.write(f"# 2. 运行测试\n")
                f.write(f"mvn test\n\n")
                f.write(f"# 3. 搜索是否还有旧包名引用\n")
                f.write(f"grep -r \"{self.old_package}.{self.class_name}\" --include=\"*.java\" .\n")
                f.write(f"```\n")
            
            print(f"📄 报告已保存: {report_file}")

def main():
    parser = argparse.ArgumentParser(description='类引用更新工具')
    parser.add_argument('--class', dest='class_name', required=True,
                        help='类名')
    parser.add_argument('--old-package', dest='old_package', required=True,
                        help='旧包名')
    parser.add_argument('--new-package', dest='new_package', required=True,
                        help='新包名')
    parser.add_argument('--dry-run', action='store_true',
                        help='试运行模式，不实际修改文件')
    
    args = parser.parse_args()
    
    print("=" * 60)
    print("类引用更新工具")
    print("=" * 60)
    print()
    
    if args.dry_run:
        print("⚠️  试运行模式 - 不会实际修改文件\n")
    
    updater = ReferenceUpdater(
        class_name=args.class_name,
        old_package=args.old_package,
        new_package=args.new_package,
        dry_run=args.dry_run
    )
    
    success = updater.scan_and_update()
    updater.generate_report()
    
    print()
    print("=" * 60)
    if success:
        print("🎉 更新完成!")
        print("\n下一步:")
        print("  1. 编译验证: mvn clean compile")
        print("  2. 运行测试: mvn test")
    else:
        print("ℹ️  未找到需要更新的引用")
    print("=" * 60)
    
    return 0

if __name__ == "__main__":
    exit(main())