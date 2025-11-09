#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Base-Core类迁移工具

功能：
1. 从base-core迁移单个类到目标模块
2. 自动更新包名
3. 更新目标模块的目录结构
4. 记录迁移日志
"""

import os
import re
import shutil
import argparse
from datetime import datetime

class ClassMigrator:
    def __init__(self, class_name, target_module, subpackage=None, dry_run=False):
        self.class_name = class_name
        self.target_module = target_module
        self.subpackage = subpackage
        self.dry_run = dry_run
        
        self.base_core_src = "jeecg-boot-base-core/src/main/java"
        self.migration_log = []
        
    def find_class_file(self):
        """在base-core中查找类文件"""
        print(f"🔍 查找类文件: {self.class_name}")
        
        for root, dirs, files in os.walk(self.base_core_src):
            for file in files:
                if file == f"{self.class_name}.java":
                    file_path = os.path.join(root, file)
                    print(f"  ✅ 找到: {file_path}")
                    return file_path
        
        print(f"  ❌ 未找到类文件: {self.class_name}.java")
        return None
    
    def extract_package_name(self, file_path):
        """提取文件的包名"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            match = re.search(r'package\s+([\w.]+);', content)
            if match:
                return match.group(1)
        except Exception as e:
            print(f"  ⚠️  读取文件失败: {e}")
        
        return None
    
    def determine_new_package(self, old_package):
        """确定新的包名"""
        # 根据目标模块确定基础包名
        base_packages = {
            "jeecg-boot-base-api": "org.jeecg.common.api",
            "jeecg-boot-base-constants": "org.jeecg.common.constant",
            "jeecg-boot-base-utils": "org.jeecg.common.util",
            "jeecg-boot-base-core-lite": "org.jeecg.common.core"
        }
        
        base_package = base_packages.get(self.target_module, "org.jeecg.common")
        
        # 如果指定了子包
        if self.subpackage:
            return f"{base_package}.{self.subpackage}"
        
        # 尝试保留原包名的最后一部分
        if old_package:
            parts = old_package.split('.')
            if len(parts) > 3:
                sub = parts[-1]  # 取最后一部分
                if sub not in ['common', 'system', 'jeecg']:
                    return f"{base_package}.{sub}"
        
        return base_package
    
    def create_target_directory(self, package_name):
        """创建目标目录"""
        target_src_base = f"{self.target_module}/src/main/java"
        package_path = package_name.replace('.', '/')
        target_dir = os.path.join(target_src_base, package_path)
        
        if not self.dry_run:
            os.makedirs(target_dir, exist_ok=True)
            print(f"  📁 创建目录: {target_dir}")
        else:
            print(f"  📁 [DRY-RUN] 将创建目录: {target_dir}")
        
        return target_dir
    
    def update_package_in_file(self, content, new_package):
        """更新文件中的包名"""
        # 替换package声明
        new_content = re.sub(
            r'package\s+[\w.]+;',
            f'package {new_package};',
            content
        )
        return new_content
    
    def migrate_class(self):
        """执行类迁移"""
        print(f"\n{'='*60}")
        print(f"迁移类: {self.class_name} -> {self.target_module}")
        print(f"{'='*60}\n")
        
        # 1. 查找源文件
        source_file = self.find_class_file()
        if not source_file:
            return False
        
        # 2. 提取原包名
        old_package = self.extract_package_name(source_file)
        if not old_package:
            print(f"  ❌ 无法提取包名")
            return False
        print(f"  原包名: {old_package}")
        
        # 3. 确定新包名
        new_package = self.determine_new_package(old_package)
        print(f"  新包名: {new_package}")
        
        # 4. 读取文件内容
        try:
            with open(source_file, 'r', encoding='utf-8') as f:
                content = f.read()
        except Exception as e:
            print(f"  ❌ 读取文件失败: {e}")
            return False
        
        # 5. 更新包名
        new_content = self.update_package_in_file(content, new_package)
        
        # 6. 创建目标目录
        target_dir = self.create_target_directory(new_package)
        target_file = os.path.join(target_dir, f"{self.class_name}.java")
        
        # 7. 写入目标文件
        if not self.dry_run:
            try:
                with open(target_file, 'w', encoding='utf-8') as f:
                    f.write(new_content)
                print(f"  ✅ 已写入: {target_file}")
            except Exception as e:
                print(f"  ❌ 写入文件失败: {e}")
                return False
        else:
            print(f"  ✅ [DRY-RUN] 将写入: {target_file}")
        
        # 8. 记录迁移信息
        migration_info = {
            "class_name": self.class_name,
            "source_file": source_file,
            "target_file": target_file,
            "old_package": old_package,
            "new_package": new_package,
            "target_module": self.target_module,
            "timestamp": datetime.now().isoformat()
        }
        self.migration_log.append(migration_info)
        
        # 9. 生成迁移记录
        self.write_migration_log(migration_info)
        
        print(f"\n✅ 迁移完成!")
        print(f"\n下一步:")
        print(f"  1. 更新其他文件中的import语句:")
        print(f"     旧: import {old_package}.{self.class_name};")
        print(f"     新: import {new_package}.{self.class_name};")
        print(f"  2. 编译验证:")
        print(f"     mvn clean compile -pl {self.target_module} -am")
        print(f"  3. 运行测试:")
        print(f"     mvn test -Dtest=*{self.class_name}*")
        
        return True
    
    def write_migration_log(self, info):
        """写入迁移日志"""
        log_file = "base-core-migration-log.md"
        
        # 检查文件是否存在
        if not os.path.exists(log_file):
            with open(log_file, 'w', encoding='utf-8') as f:
                f.write("# Base-Core类迁移日志\n\n")
                f.write("| 时间 | 类名 | 原包名 | 新包名 | 目标模块 |\n")
                f.write("|------|------|--------|--------|----------|\n")
        
        # 追加迁移记录
        if not self.dry_run:
            with open(log_file, 'a', encoding='utf-8') as f:
                timestamp = datetime.fromisoformat(info['timestamp']).strftime('%Y-%m-%d %H:%M:%S')
                f.write(f"| {timestamp} | `{info['class_name']}` | `{info['old_package']}` | `{info['new_package']}` | {info['target_module']} |\n")
            print(f"  📝 已记录到: {log_file}")

def main():
    parser = argparse.ArgumentParser(description='Base-Core类迁移工具')
    parser.add_argument('--class', dest='class_name', required=True,
                        help='要迁移的类名（不含.java后缀）')
    parser.add_argument('--target', dest='target_module', required=True,
                        choices=[
                            'jeecg-boot-base-api',
                            'jeecg-boot-base-constants',
                            'jeecg-boot-base-utils',
                            'jeecg-boot-base-core-lite'
                        ],
                        help='目标模块')
    parser.add_argument('--subpackage', dest='subpackage',
                        help='子包名（可选，如：vo, annotation等）')
    parser.add_argument('--dry-run', action='store_true',
                        help='试运行模式，不实际执行')
    
    args = parser.parse_args()
    
    print("=" * 60)
    print("Base-Core类迁移工具")
    print("=" * 60)
    print()
    
    if args.dry_run:
        print("⚠️  试运行模式 - 不会实际修改文件\n")
    
    migrator = ClassMigrator(
        class_name=args.class_name,
        target_module=args.target_module,
        subpackage=args.subpackage,
        dry_run=args.dry_run
    )
    
    success = migrator.migrate_class()
    
    print()
    print("=" * 60)
    if success:
        print("🎉 迁移成功!")
    else:
        print("❌ 迁移失败!")
    print("=" * 60)
    
    return 0 if success else 1

if __name__ == "__main__":
    exit(main())