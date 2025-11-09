#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
POM版本号统一脚本 - 统一到4.0.0-SNAPSHOT
功能: 将所有3.8.3版本统一升级为4.0.0-SNAPSHOT
"""

import os
import re
from pathlib import Path

# 配置
OLD_VERSION = "3.8.3"
NEW_VERSION = "4.0.0-SNAPSHOT"
ROOT_DIR = "."

def update_pom_version(file_path):
    """更新单个POM文件的版本号"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        changes = []
        
        # 1. 更新父POM版本
        pattern1 = r'(<parent>.*?<artifactId>jeecg-boot-parent</artifactId>\s*<version>)3\.8\.3(</version>.*?</parent>)'
        if re.search(pattern1, content, re.DOTALL):
            content = re.sub(pattern1, r'\g<1>4.0.0-SNAPSHOT\g<2>', content, flags=re.DOTALL)
            changes.append("父POM版本")
        
        # 2. 更新模块自己的version标签（在parent标签之后）
        pattern2 = r'(</parent>.*?<version>)3\.8\.3(</version>)'
        if re.search(pattern2, content, re.DOTALL):
            content = re.sub(pattern2, r'\g<1>4.0.0-SNAPSHOT\g<2>', content, count=1, flags=re.DOTALL)
            changes.append("模块版本")
        
        # 3. 更新properties中的版本变量
        pattern3 = r'(<jeecgboot\.version>)3\.8\.3(</jeecgboot\.version>)'
        if re.search(pattern3, content):
            content = re.sub(pattern3, r'\g<1>4.0.0-SNAPSHOT\g<2>', content)
            changes.append("jeecgboot.version属性")
        
        # 4. 更新dependencyManagement中jeecg模块的版本
        pattern4 = r'(<groupId>org\.jeecgframework\.boot3</groupId>\s*<artifactId>jeecg-[^<]+</artifactId>\s*<version>)3\.8\.3(</version>)'
        if re.search(pattern4, content, re.DOTALL):
            content = re.sub(pattern4, r'\g<1>4.0.0-SNAPSHOT\g<2>', content, flags=re.DOTALL)
            changes.append("依赖管理版本")
        
        # 5. 更新dependencies中jeecg模块的版本（如果显式指定）
        pattern5 = r'(<dependency>.*?<groupId>org\.jeecgframework\.boot3</groupId>\s*<artifactId>jeecg-[^<]+</artifactId>\s*<version>)3\.8\.3(</version>.*?</dependency>)'
        if re.search(pattern5, content, re.DOTALL):
            content = re.sub(pattern5, r'\g<1>4.0.0-SNAPSHOT\g<2>', content, flags=re.DOTALL)
            changes.append("依赖版本")
        
        # 如果有变更，写回文件
        if content != original_content:
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            return True, changes
        
        return False, []
        
    except Exception as e:
        print(f"❌ 处理文件失败 {file_path}: {str(e)}")
        return False, []

def find_pom_files(root_dir):
    """递归查找所有pom.xml文件"""
    pom_files = []
    for root, dirs, files in os.walk(root_dir):
        # 排除target目录和.git目录
        if 'target' in root or '.git' in root:
            continue
        if 'pom.xml' in files:
            pom_files.append(os.path.join(root, 'pom.xml'))
    return sorted(pom_files)

def main():
    print("=" * 70)
    print("🚀 POM版本号统一脚本 - 升级到4.0.0-SNAPSHOT")
    print(f"   将 {OLD_VERSION} 统一升级为 {NEW_VERSION}")
    print("=" * 70)
    print()
    
    # 查找所有POM文件
    pom_files = find_pom_files(ROOT_DIR)
    print(f"📁 找到 {len(pom_files)} 个 pom.xml 文件\n")
    
    # 更新版本号
    updated_count = 0
    skipped_count = 0
    
    for pom_file in pom_files:
        updated, changes = update_pom_version(pom_file)
        if updated:
            updated_count += 1
            print(f"✅ {pom_file}")
            if changes:
                print(f"   📝 更新内容: {', '.join(changes)}")
        else:
            skipped_count += 1
            # print(f"⏭️  {pom_file} (已是最新版本或无需更新)")
    
    print()
    print("=" * 70)
    print(f"📊 统计结果:")
    print(f"   ✅ 已更新: {updated_count} 个文件")
    print(f"   ⏭️  跳过: {skipped_count} 个文件")
    print(f"   📦 总计: {len(pom_files)} 个文件")
    print("=" * 70)
    
    if updated_count > 0:
        print()
        print("🎯 下一步操作:")
        print("   1️⃣  检查修改: git diff pom.xml")
        print("   2️⃣  验证构建: mvn clean install -DskipTests")
        print("   3️⃣  提交变更: git add . && git commit -m 'chore: 统一所有模块版本号为4.0.0-SNAPSHOT'")
        print()
        print("⚠️  注意事项:")
        print("   - 升级到4.0.0-SNAPSHOT表示这是新架构的快照版本")
        print("   - 建议在独立分支进行测试")
        print("   - 确保所有模块都能正常构建")

if __name__ == '__main__':
    main()