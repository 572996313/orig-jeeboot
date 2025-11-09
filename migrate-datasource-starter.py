#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Phase 17.2 - Datasource Starter 迁移脚本
策略：渐进式备份 - 保留3个简单配置类，备份2个复杂工具类
"""

import os
import shutil
from pathlib import Path

# 源目录
BASE_CORE = Path("jeecg-boot-base-core/src/main/java/org/jeecg")
# 目标目录
DATASOURCE_STARTER = Path("jeecg-boot-starter-datasource/src/main/java/org/jeecg")

# 待迁移文件
files_to_migrate = {
    # 简单配置类 - 直接迁移
    "simple": [
        ("config/DruidConfig.java", "config/DruidConfig.java"),
        ("config/DruidWallConfigRegister.java", "config/DruidWallConfigRegister.java"),
        ("config/CorsFilterCondition.java", "config/CorsFilterCondition.java"),
    ],
    # 复杂工具类 - 需要备份
    "complex": [
        ("common/util/dynamic/db/DataSourceCachePool.java", "util/dynamic/db/DataSourceCachePool.java"),
        ("common/util/dynamic/db/DynamicDBUtil.java", "util/dynamic/db/DynamicDBUtil.java"),
    ]
}

def migrate_files():
    """迁移所有文件"""
    print("=" * 80)
    print("Phase 17.2 - Datasource Starter 文件迁移")
    print("=" * 80)
    
    migrated_count = 0
    
    # 1. 迁移简单类
    print("\n【步骤1】迁移简单配置类...")
    for src_rel, dst_rel in files_to_migrate["simple"]:
        src = BASE_CORE / src_rel
        dst = DATASOURCE_STARTER / dst_rel
        
        if not src.exists():
            print(f"  ⚠️  源文件不存在: {src}")
            continue
            
        # 创建目标目录
        dst.parent.mkdir(parents=True, exist_ok=True)
        
        # 复制文件
        shutil.copy2(src, dst)
        print(f"  ✅ {src_rel} → {dst_rel}")
        migrated_count += 1
    
    # 2. 迁移复杂类（稍后会备份）
    print("\n【步骤2】迁移复杂工具类（稍后备份）...")
    for src_rel, dst_rel in files_to_migrate["complex"]:
        src = BASE_CORE / src_rel
        dst = DATASOURCE_STARTER / dst_rel
        
        if not src.exists():
            print(f"  ⚠️  源文件不存在: {src}")
            continue
            
        # 创建目标目录
        dst.parent.mkdir(parents=True, exist_ok=True)
        
        # 复制文件
        shutil.copy2(src, dst)
        print(f"  ⚠️  {src_rel} → {dst_rel} (待备份)")
        migrated_count += 1
    
    print(f"\n✅ 总计迁移: {migrated_count} 个文件")
    print(f"   - 简单类: {len(files_to_migrate['simple'])} 个")
    print(f"   - 复杂类: {len(files_to_migrate['complex'])} 个 (待备份)")
    
    return migrated_count

def create_autoconfiguration():
    """创建自动配置类"""
    print("\n【步骤3】创建自动配置类...")
    
    # 创建autoconfigure目录
    autoconfigure_dir = DATASOURCE_STARTER / "autoconfigure"
    autoconfigure_dir.mkdir(parents=True, exist_ok=True)
    
    # 1. 创建配置属性类
    properties_file = autoconfigure_dir / "JeecgDatasourceProperties.java"
    properties_content = '''package org.jeecg.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jeecg Datasource 配置属性
 */
@Data
@ConfigurationProperties(prefix = "jeecg.datasource")
public class JeecgDatasourceProperties {
    
    /**
     * 是否启用动态数据源
     */
    private boolean dynamicEnabled = false;
    
    /**
     * Druid监控是否启用
     */
    private boolean druidMonitorEnabled = true;
    
    /**
     * Druid监控登录用户名
     */
    private String druidMonitorUsername = "admin";
    
    /**
     * Druid监控登录密码
     */
    private String druidMonitorPassword = "123456";
    
    /**
     * 是否去除Druid广告
     */
    private boolean removeAdEnabled = true;
}
'''
    properties_file.write_text(properties_content, encoding='utf-8')
    print(f"  ✅ 创建: JeecgDatasourceProperties.java")
    
    # 2. 创建自动配置类
    autoconfig_file = autoconfigure_dir / "JeecgDatasourceAutoConfiguration.java"
    autoconfig_content = '''package org.jeecg.autoconfigure;

import org.jeecg.config.DruidConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Jeecg Datasource 自动配置
 */
@AutoConfiguration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(JeecgDatasourceProperties.class)
@Import({DruidConfig.class})
public class JeecgDatasourceAutoConfiguration {
    
    // DruidConfig会通过@Import自动注入
    // 其他动态数据源相关配置稍后恢复
}
'''
    autoconfig_file.write_text(autoconfig_content, encoding='utf-8')
    print(f"  ✅ 创建: JeecgDatasourceAutoConfiguration.java")
    
    return 2

def create_spring_factories():
    """创建Spring Boot自动配置文件"""
    print("\n【步骤4】创建spring.factories...")
    
    resources_dir = Path("jeecg-boot-starter-datasource/src/main/resources/META-INF")
    resources_dir.mkdir(parents=True, exist_ok=True)
    
    factories_file = resources_dir / "spring.factories"
    factories_content = '''# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\\
org.jeecg.autoconfigure.JeecgDatasourceAutoConfiguration
'''
    factories_file.write_text(factories_content, encoding='utf-8')
    print(f"  ✅ 创建: spring.factories")
    
    return 1

if __name__ == "__main__":
    total = 0
    total += migrate_files()
    total += create_autoconfiguration()
    total += create_spring_factories()
    
    print("\n" + "=" * 80)
    print(f"✅ Phase 17.2 完成！总计创建/迁移 {total} 个文件")
    print("=" * 80)
    print("\n📝 下一步：")
    print("  1. 执行 mvn clean install")
    print("  2. 备份有依赖问题的2个复杂类")
    print("  3. 创建备份说明文档")