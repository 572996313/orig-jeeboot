#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
移除 jeecg-boot-base-core 模块
该模块已被 jeecg-boot-base-core-aggregator 替代
"""

import os
import shutil
import sys

def remove_base_core_module():
    """删除 jeecg-boot-base-core 模块目录"""
    
    module_path = "jeecg-boot-base-core"
    
    print("=" * 80)
    print("移除 jeecg-boot-base-core 模块")
    print("=" * 80)
    
    if not os.path.exists(module_path):
        print(f"✗ 模块目录不存在: {module_path}")
        return False
    
    try:
        # 显示将要删除的内容
        print(f"\n📁 准备删除目录: {module_path}")
        
        # 统计文件数量
        total_files = 0
        total_dirs = 0
        for root, dirs, files in os.walk(module_path):
            total_files += len(files)
            total_dirs += len(dirs)
        
        print(f"   - 包含 {total_dirs} 个子目录")
        print(f"   - 包含 {total_files} 个文件")
        
        # 删除目录
        print(f"\n🗑️  正在删除...")
        shutil.rmtree(module_path)
        
        print(f"✓ 成功删除模块目录: {module_path}")
        
        print("\n" + "=" * 80)
        print("模块移除完成！")
        print("=" * 80)
        print("\n📋 后续步骤:")
        print("   1. 运行: mvn clean install -DskipTests")
        print("   2. 验证构建是否成功")
        print("   3. 所有依赖已自动切换到 jeecg-boot-base-core-aggregator")
        print("\n✨ jeecg-boot-base-core 模块已完全从项目中移除")
        
        return True
        
    except Exception as e:
        print(f"✗ 删除失败: {str(e)}")
        return False

if __name__ == "__main__":
    success = remove_base_core_module()
    sys.exit(0 if success else 1)