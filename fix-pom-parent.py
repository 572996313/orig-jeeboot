#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
批量修复所有新模块的 parent 配置
统一修改为: org.jeecgframework.boot3:jeecg-boot-parent:3.8.3
"""

import os
import re

# 需要修复的模块列表
modules = [
    'jeecg-boot-starter-datasource',
    'jeecg-boot-starter-mybatis-plus',
    'jeecg-boot-starter-oss',
    'jeecg-boot-starter-excel',
    'jeecg-boot-starter-desensitization',
    'jeecg-boot-starter-communication',
    'jeecg-boot-starter-elasticsearch',
    'jeecg-boot-starter-web',
    'jeecg-boot-base-core-aggregator',
    'jeecg-boot-base-constants',
    'jeecg-boot-base-api',
    'jeecg-boot-starter-api-doc',
]

def fix_pom(module_name):
    pom_path = os.path.join(module_name, 'pom.xml')
    
    if not os.path.exists(pom_path):
        print(f"❌ {pom_path} 不存在，跳过")
        return False
    
    with open(pom_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    original_content = content
    
    # 1. 修复 parent groupId
    content = re.sub(
        r'<parent>\s*<groupId>org\.jeecgframework\.boot</groupId>',
        '<parent>\n        <groupId>org.jeecgframework.boot3</groupId>',
        content
    )
    
    # 2. 修复 parent version (4.0.0-SNAPSHOT -> 3.8.3, 4.0.0 -> 3.8.3)
    content = re.sub(
        r'(<parent>.*?<version>)(4\.0\.0-SNAPSHOT|4\.0\.0)(</version>)',
        r'\g<1>3.8.3\g<3>',
        content,
        flags=re.DOTALL
    )
    
    # 3. 在 parent 后添加 groupId (如果缺失)
    if '<parent>' in content and '</parent>' in content:
        # 检查是否在 parent 后有 groupId
        parent_section = re.search(r'</parent>\s*<artifactId>', content)
        if parent_section:
            content = re.sub(
                r'(</parent>\s*)(<artifactId>)',
                r'\1\n    <groupId>org.jeecgframework.boot3</groupId>\n    \2',
                content,
                count=1
            )
    
    # 4. 修复内部依赖的 groupId
    content = re.sub(
        r'<groupId>org\.jeecgframework\.boot</groupId>\s*<artifactId>(jeecg-boot-base-|jeecg-boot-starter-)',
        r'<groupId>org.jeecgframework.boot3</groupId>\n            <artifactId>\1',
        content
    )
    
    # 5. 移除内部依赖的显式版本号
    content = re.sub(
        r'(<artifactId>jeecg-boot-(?:base-|starter-)[^<]+</artifactId>)\s*<version>\$\{project\.version\}</version>',
        r'\1',
        content
    )
    
    # 6. 修复 parent artifactId (jeecg-boot-base -> jeecg-boot-parent)
    content = re.sub(
        r'(<parent>.*?<artifactId>)jeecg-boot-base(</artifactId>)',
        r'\1jeecg-boot-parent\2',
        content,
        flags=re.DOTALL
    )
    
    if content != original_content:
        with open(pom_path, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"✅ 已修复: {pom_path}")
        return True
    else:
        print(f"⏭️  无需修改: {pom_path}")
        return False

def main():
    print("="*60)
    print("开始批量修复 POM 文件的 parent 配置")
    print("="*60)
    
    fixed_count = 0
    for module in modules:
        if fix_pom(module):
            fixed_count += 1
    
    print("="*60)
    print(f"修复完成! 共修复 {fixed_count}/{len(modules)} 个模块")
    print("="*60)

if __name__ == '__main__':
    main()