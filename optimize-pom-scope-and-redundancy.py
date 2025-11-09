#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
POM优化脚本 - P3任务：优化依赖scope和移除冗余
优化内容：
1. 优化Starter模块的依赖scope（provided/compile）
2. 移除重复和冗余的依赖声明
3. 确保传递性依赖的正确性
"""

import os
import re
from pathlib import Path

# 需要优化scope的依赖规则
SCOPE_OPTIMIZATION_RULES = {
    # 编译时需要，运行时由使用方提供
    'provided': [
        'lombok',
        'spring-boot-configuration-processor',
        'jakarta.servlet-api',
        'javax.servlet-api',
    ],
    # 传递依赖，应该由父依赖提供
    'should_remove_if_transitive': [
        'slf4j-api',  # 由spring-boot-starter提供
        'jackson-databind',  # 由spring-boot-starter-web提供
        'fastjson',  # 如果已在父POM管理
    ]
}

# Starter模块特定规则
STARTER_SPECIFIC_RULES = {
    'jeecg-boot-starter-datasource': {
        'provided': ['mysql-connector-j', 'postgresql', 'oracle-jdbc'],  # 数据库驱动由使用方选择
    },
    'jeecg-boot-starter-communication': {
        'optional': ['aliyun-java-sdk-core', 'aliyun-java-sdk-dysmsapi'],  # 可选的短信服务
    },
    'jeecg-boot-starter-oss': {
        'optional': ['aliyun-oss', 'minio'],  # 可选的OSS服务
    }
}

def read_pom(file_path):
    """读取POM文件"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            return f.read()
    except Exception as e:
        print(f"❌ 读取文件失败 {file_path}: {e}")
        return None

def write_pom(file_path, content):
    """写入POM文件"""
    try:
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    except Exception as e:
        print(f"❌ 写入文件失败 {file_path}: {e}")
        return False

def optimize_dependency_scope(content, module_name):
    """优化依赖的scope"""
    changes = []
    
    # 查找所有依赖块
    dependency_pattern = re.compile(
        r'(<dependency>.*?</dependency>)',
        re.DOTALL
    )
    
    def process_dependency(match):
        dep_block = match.group(1)
        original_block = dep_block
        
        # 提取artifactId
        artifact_match = re.search(r'<artifactId>(.*?)</artifactId>', dep_block)
        if not artifact_match:
            return dep_block
        
        artifact_id = artifact_match.group(1)
        
        # 检查是否需要添加provided scope
        if artifact_id in SCOPE_OPTIMIZATION_RULES['provided']:
            # 检查是否已有scope标签
            if '<scope>' not in dep_block:
                # 在</dependency>前添加scope
                dep_block = dep_block.replace(
                    '</dependency>',
                    '            <scope>provided</scope>\n        </dependency>'
                )
                changes.append(f"  ✓ {artifact_id}: 添加 <scope>provided</scope>")
            elif '<scope>provided</scope>' not in dep_block:
                # 替换现有scope
                dep_block = re.sub(
                    r'<scope>.*?</scope>',
                    '<scope>provided</scope>',
                    dep_block
                )
                changes.append(f"  ✓ {artifact_id}: 修改为 <scope>provided</scope>")
        
        # 检查模块特定规则
        if module_name in STARTER_SPECIFIC_RULES:
            rules = STARTER_SPECIFIC_RULES[module_name]
            
            # 处理provided规则
            if 'provided' in rules and artifact_id in rules['provided']:
                if '<scope>provided</scope>' not in dep_block:
                    if '<scope>' in dep_block:
                        dep_block = re.sub(r'<scope>.*?</scope>', '<scope>provided</scope>', dep_block)
                    else:
                        dep_block = dep_block.replace('</dependency>', '            <scope>provided</scope>\n        </dependency>')
                    changes.append(f"  ✓ {artifact_id}: 设置为 provided (模块规则)")
            
            # 处理optional规则
            if 'optional' in rules and artifact_id in rules['optional']:
                if '<optional>true</optional>' not in dep_block:
                    dep_block = dep_block.replace('</dependency>', '            <optional>true</optional>\n        </dependency>')
                    changes.append(f"  ✓ {artifact_id}: 设置为 optional")
        
        return dep_block
    
    new_content = dependency_pattern.sub(process_dependency, content)
    
    return new_content, changes

def remove_redundant_dependencies(content):
    """移除冗余的传递依赖"""
    changes = []
    
    # 查找并标记应该移除的传递依赖
    for artifact_id in SCOPE_OPTIMIZATION_RULES['should_remove_if_transitive']:
        # 查找该依赖
        pattern = re.compile(
            rf'(<dependency>\s*<groupId>.*?</groupId>\s*<artifactId>{re.escape(artifact_id)}</artifactId>.*?</dependency>)',
            re.DOTALL
        )
        
        matches = pattern.findall(content)
        if matches:
            for match in matches:
                # 检查是否没有scope或scope为compile
                if '<scope>' not in match or '<scope>compile</scope>' in match:
                    # 不直接移除，而是添加注释说明这是传递依赖
                    commented = f'<!-- 传递依赖：{artifact_id} 由其他依赖提供，可考虑移除 -->\n        ' + match
                    content = content.replace(match, commented)
                    changes.append(f"  ⚠ {artifact_id}: 标记为可移除的传递依赖")
    
    return content, changes

def optimize_starter_pom(pom_path):
    """优化单个Starter模块的POM"""
    module_name = os.path.basename(os.path.dirname(pom_path))
    print(f"\n{'='*60}")
    print(f"📦 优化模块: {module_name}")
    print(f"{'='*60}")
    
    content = read_pom(pom_path)
    if not content:
        return False
    
    original_content = content
    all_changes = []
    
    # 1. 优化依赖scope
    print("\n1️⃣ 优化依赖scope...")
    content, scope_changes = optimize_dependency_scope(content, module_name)
    if scope_changes:
        all_changes.extend(scope_changes)
        for change in scope_changes:
            print(change)
    else:
        print("  ℹ 无需优化")
    
    # 2. 移除冗余依赖
    print("\n2️⃣ 检查冗余依赖...")
    content, redundancy_changes = remove_redundant_dependencies(content)
    if redundancy_changes:
        all_changes.extend(redundancy_changes)
        for change in redundancy_changes:
            print(change)
    else:
        print("  ℹ 未发现冗余依赖")
    
    # 写入文件
    if content != original_content:
        if write_pom(pom_path, content):
            print(f"\n✅ 成功优化 {module_name} ({len(all_changes)} 处修改)")
            return True
        else:
            print(f"\n❌ 写入失败 {module_name}")
            return False
    else:
        print(f"\n✓ {module_name} 无需修改")
        return True

def main():
    """主函数"""
    print("="*70)
    print("POM优化工具 - P3任务: 依赖scope优化和冗余移除")
    print("="*70)
    
    # 查找所有Starter模块的POM文件
    starter_poms = []
    for root, dirs, files in os.walk('.'):
        if 'pom.xml' in files and 'starter' in root:
            pom_path = os.path.join(root, 'pom.xml')
            # 排除target目录
            if 'target' not in pom_path and 'backup' not in pom_path:
                starter_poms.append(pom_path)
    
    print(f"\n找到 {len(starter_poms)} 个Starter模块")
    
    success_count = 0
    for pom_path in sorted(starter_poms):
        if optimize_starter_pom(pom_path):
            success_count += 1
    
    # 输出总结
    print("\n" + "="*70)
    print("优化完成总结")
    print("="*70)
    print(f"✅ 成功优化: {success_count}/{len(starter_poms)} 个模块")
    
    if success_count < len(starter_poms):
        print(f"❌ 失败: {len(starter_poms) - success_count} 个模块")
    
    print("\n建议操作:")
    print("1. 执行 mvn clean compile 验证编译")
    print("2. 检查标记为'可移除'的传递依赖")
    print("3. 测试应用启动和运行")

if __name__ == '__main__':
    main()