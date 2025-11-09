#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
恢复所有模块的备份文件
Phase 20: 批量恢复64个备份文件
"""

import os
import shutil
from pathlib import Path

# 定义所有需要恢复备份的模块
MODULES_WITH_BACKUPS = {
    'jeecg-boot-base-constants': 'backup-phase9',
    'jeecg-boot-base-api': 'backup-phase11',
    'jeecg-boot-base-utils': 'backup-phase14',
    'jeecg-boot-base-core-lite': 'backup-phase15',
    'jeecg-boot-starter-security': 'backup-phase17.1',
    'jeecg-boot-starter-datasource': 'backup-phase17.2',
    'jeecg-boot-starter-mybatis-plus': 'backup-phase17.3',
    'jeecg-boot-starter-oss': 'backup-phase17.4',
    'jeecg-boot-starter-api-doc': 'backup-phase17.5',
    'jeecg-boot-starter-communication': 'backup-phase17.8',
    'jeecg-boot-starter-elasticsearch': 'backup-phase17.9',
    'jeecg-boot-starter-web': 'backup-phase17.10',
}

def restore_backup(module_name, backup_dir_name):
    """恢复单个模块的备份文件"""
    module_path = Path(module_name)
    backup_path = module_path / backup_dir_name
    
    if not backup_path.exists():
        print(f"⚠️  警告: {module_name} 的备份目录不存在: {backup_path}")
        return 0
    
    print(f"\n📦 恢复模块: {module_name}")
    print(f"   备份目录: {backup_dir_name}")
    
    restored_count = 0
    
    # 遍历备份目录中的所有文件
    for backup_file in backup_path.rglob('*.java'):
        # 计算相对路径
        rel_path = backup_file.relative_to(backup_path)
        
        # 目标文件路径
        target_file = module_path / rel_path
        
        # 确保目标目录存在
        target_file.parent.mkdir(parents=True, exist_ok=True)
        
        # 复制文件
        shutil.copy2(backup_file, target_file)
        print(f"   ✅ 恢复: {rel_path}")
        restored_count += 1
    
    print(f"   ✨ 完成! 恢复了 {restored_count} 个文件")
    return restored_count

def main():
    """主函数"""
    print("=" * 70)
    print("🔄 Phase 20: 恢复所有备份文件")
    print("=" * 70)
    
    total_restored = 0
    module_count = 0
    
    for module_name, backup_dir in MODULES_WITH_BACKUPS.items():
        count = restore_backup(module_name, backup_dir)
        if count > 0:
            module_count += 1
            total_restored += count
    
    print("\n" + "=" * 70)
    print(f"✅ 恢复完成!")
    print(f"   处理模块数: {module_count}")
    print(f"   恢复文件数: {total_restored}")
    print("=" * 70)
    
    # 生成报告
    generate_report(module_count, total_restored)

def generate_report(module_count, file_count):
    """生成恢复报告"""
    report_content = f"""# Phase 20: 备份文件恢复报告

## 执行时间
{Path(__file__).stat().st_mtime}

## 恢复统计

- **处理模块数**: {module_count}
- **恢复文件数**: {file_count}

## 模块详情

| 模块名 | 备份目录 | 状态 |
|--------|---------|------|
"""
    
    for module_name, backup_dir in MODULES_WITH_BACKUPS.items():
        backup_path = Path(module_name) / backup_dir
        if backup_path.exists():
            file_count = len(list(backup_path.rglob('*.java')))
            report_content += f"| {module_name} | {backup_dir} | ✅ {file_count}个文件 |\n"
        else:
            report_content += f"| {module_name} | {backup_dir} | ⚠️  备份不存在 |\n"
    
    report_content += """
## 下一步

1. ✅ 所有备份文件已恢复
2. 🔄 需要重新编译所有模块
3. 🧪 执行 Phase 21: 集成测试

## 命令

```bash
# 重新编译所有模块
mvn clean install -DskipTests

# 或者逐个模块编译
cd jeecg-boot-base-constants && mvn clean install -DskipTests
cd ../jeecg-boot-base-api && mvn clean install -DskipTests
# ... 依此类推
```
"""
    
    # 写入报告文件
    with open('PHASE_20_RESTORE_REPORT.md', 'w', encoding='utf-8') as f:
        f.write(report_content)
    
    print(f"\n📄 恢复报告已生成: PHASE_20_RESTORE_REPORT.md")

if __name__ == '__main__':
    main()