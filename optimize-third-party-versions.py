#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
统一第三方库版本脚本
功能: 移除子模块中硬编码的版本号,统一使用父POM的版本管理
"""

import os
import re
from pathlib import Path

ROOT_DIR = "."

# 需要统一管理的依赖(从子模块移除硬编码版本)
DEPENDENCIES_TO_CLEAN = [
    # 工具库
    ('cn.hutool', 'hutool-all'),
    ('cn.hutool', 'hutool-core'),
    ('cn.hutool', 'hutool-crypto'),
    
    # JSON库
    ('com.alibaba', 'fastjson'),
    ('com.alibaba.fastjson2', 'fastjson2'),
    
    # MyBatis-Plus
    ('com.baomidou', 'mybatis-plus-spring-boot3-starter'),
    ('com.baomidou', 'mybatis-plus-boot-starter'),
    ('com.baomidou', 'mybatis-plus-extension'),
    ('com.baomidou', 'mybatis-plus-core'),
    
    # SQL解析器
    ('com.github.jsqlparser', 'jsqlparser'),
    
    # 数据源
    ('com.alibaba', 'druid-spring-boot-3-starter'),
    ('com.baomidou', 'dynamic-datasource-spring-boot3-starter'),
    
    # 对象存储
    ('io.minio', 'minio'),
    ('com.aliyun.oss', 'aliyun-sdk-oss'),
    
    # API文档
    ('com.github.xiaoymin', 'knife4j-openapi3-spring-boot-starter'),
    ('com.github.xiaoymin', 'knife4j-openapi3-ui'),
    ('org.springdoc', 'springdoc-openapi-starter-webmvc-ui'),
    ('io.springfox', 'springfox-boot-starter'),
    
    # 其他
    ('commons-beanutils', 'commons-beanutils'),
    ('commons-fileupload', 'commons-fileupload'),
]

def clean_dependency_version(content, group_id, artifact_id):
    """移除指定依赖的version标签"""
    # 匹配依赖块并移除version标签
    pattern = (
        rf'(<dependency>\s*'
        rf'<groupId>{re.escape(group_id)}</groupId>\s*'
        rf'<artifactId>{re.escape(artifact_id)}</artifactId>\s*)'
        rf'<version>[^<]+</version>(\s*)'
    )
    
    replacement = r'\1\2'
    new_content = re.sub(pattern, replacement, content, flags=re.DOTALL)
    
    return new_content, new_content != content

def update_pom_file(file_path):
    """更新单个POM文件"""
    # 跳过父POM
    if file_path.endswith(os.path.join('.', 'pom.xml')):
        return False, []
    
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        cleaned = []
        
        for group_id, artifact_id in DEPENDENCIES_TO_CLEAN:
            content, changed = clean_dependency_version(content, group_id, artifact_id)
            if changed:
                cleaned.append(f"{group_id}:{artifact_id}")
        
        # 如果有变更,写回文件
        if content != original_content:
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            return True, cleaned
        
        return False, []
        
    except Exception as e:
        print(f"❌ 处理文件失败 {file_path}: {str(e)}")
        return False, []

def find_pom_files(root_dir):
    """递归查找所有pom.xml文件"""
    pom_files = []
    for root, dirs, files in os.walk(root_dir):
        if 'target' in root or '.git' in root:
            continue
        if 'pom.xml' in files:
            pom_file = os.path.join(root, 'pom.xml')
            # 跳过根目录的pom.xml
            if pom_file != os.path.join(root_dir, 'pom.xml'):
                pom_files.append(pom_file)
    return sorted(pom_files)

def main():
    print("=" * 70)
    print("🔧 统一第三方库版本脚本")
    print("   移除子模块中的硬编码版本,统一使用父POM管理")
    print("=" * 70)
    print()
    
    # 查找所有POM文件(除了根POM)
    pom_files = find_pom_files(ROOT_DIR)
    print(f"📁 找到 {len(pom_files)} 个子模块pom.xml文件\n")
    
    # 更新版本号
    updated_count = 0
    total_cleaned = 0
    
    for pom_file in pom_files:
        updated, cleaned = update_pom_file(pom_file)
        if updated:
            updated_count += 1
            total_cleaned += len(cleaned)
            print(f"✅ {pom_file}")
            print(f"   📝 清理了 {len(cleaned)} 个硬编码版本:")
            for dep in cleaned[:3]:  # 只显示前3个
                print(f"      - {dep}")
            if len(cleaned) > 3:
                print(f"      ... 还有 {len(cleaned) - 3} 个")
    
    print()
    print("=" * 70)
    print(f"📊 统计结果:")
    print(f"   ✅ 已更新: {updated_count} 个文件")
    print(f"   🧹 清理: {total_cleaned} 个硬编码版本")
    print(f"   📦 检查: {len(pom_files)} 个文件")
    print("=" * 70)
    
    if updated_count > 0:
        print()
        print("🎯 下一步操作:")
        print("   1️⃣  检查修改: git diff")
        print("   2️⃣  验证父POM有这些依赖的版本管理")
        print("   3️⃣  验证构建: mvn clean install -DskipTests")
        print("   4️⃣  提交变更: git commit -am 'chore: 统一第三方库版本管理'")
        print()
        print("✅ 优化效果:")
        print("   - 版本统一管理,避免冲突")
        print("   - 子模块POM更简洁")
        print("   - 更容易升级依赖版本")

if __name__ == '__main__':
    main()