#!/usr/bin/env python3
"""
自动化迁移脚本 - jeecg-boot-starter-security模块
从jeecg-boot-base-core复制Shiro相关文件到新的security starter模块
"""

import os
import shutil
from pathlib import Path

# 源路径和目标路径
SOURCE_BASE = "jeecg-boot-base-core/src/main/java/org/jeecg"
TARGET_BASE = "jeecg-boot-starter-security/src/main/java/org/jeecg"

# 需要迁移的文件映射
FILE_MAPPINGS = [
    # Shiro配置类
    ("config/shiro/ShiroConfig.java", "config/shiro/ShiroConfig.java"),
    ("config/shiro/ShiroRealm.java", "config/shiro/ShiroRealm.java"),
    ("config/shiro/IgnoreAuth.java", "config/shiro/IgnoreAuth.java"),
    ("config/shiro/JwtToken.java", "config/shiro/JwtToken.java"),
    
    # Shiro过滤器
    ("config/shiro/filters/CustomShiroFilterFactoryBean.java", "config/shiro/filters/CustomShiroFilterFactoryBean.java"),
    ("config/shiro/filters/JwtFilter.java", "config/shiro/filters/JwtFilter.java"),
    ("config/shiro/filters/ResourceCheckFilter.java", "config/shiro/filters/ResourceCheckFilter.java"),
    
    # Ignore认证处理器
    ("config/shiro/ignore/IgnoreAuthPostProcessor.java", "config/shiro/ignore/IgnoreAuthPostProcessor.java"),
    ("config/shiro/ignore/InMemoryIgnoreAuth.java", "config/shiro/ignore/InMemoryIgnoreAuth.java"),
    
    # JWT工具类 (需要重构,暂时复制)
    ("common/system/util/JwtUtil.java", "common/system/util/JwtUtil.java"),
]

def create_directory_structure():
    """创建目标目录结构"""
    print("📁 创建目录结构...")
    
    directories = [
        f"{TARGET_BASE}/config/shiro/filters",
        f"{TARGET_BASE}/config/shiro/ignore",
        f"{TARGET_BASE}/common/system/util",
        f"{TARGET_BASE}/autoconfigure",
    ]
    
    for directory in directories:
        Path(directory).mkdir(parents=True, exist_ok=True)
        print(f"   ✓ {directory}")

def copy_files():
    """复制文件"""
    print("\n📋 复制文件...")
    
    copied_count = 0
    failed_files = []
    
    for source_rel, target_rel in FILE_MAPPINGS:
        source_path = Path(SOURCE_BASE) / source_rel
        target_path = Path(TARGET_BASE) / target_rel
        
        if source_path.exists():
            try:
                shutil.copy2(source_path, target_path)
                print(f"   ✓ {source_rel}")
                copied_count += 1
            except Exception as e:
                print(f"   ✗ {source_rel} - 错误: {e}")
                failed_files.append(source_rel)
        else:
            print(f"   ⚠ {source_rel} - 文件不存在")
            failed_files.append(source_rel)
    
    return copied_count, failed_files

def create_autoconfiguration():
    """创建自动配置类"""
    print("\n🔧 创建自动配置类...")
    
    autoconfigure_content = '''package org.jeecg.autoconfigure;

import org.jeecg.config.shiro.ShiroConfig;
import org.jeecg.config.shiro.ShiroRealm;
import org.jeecg.config.shiro.ignore.IgnoreAuthPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * Jeecg Security 自动配置类
 * 
 * @author jeecg-boot
 * @version 1.0
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "jeecg.security", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import({
    ShiroConfig.class,
    ShiroRealm.class,
    IgnoreAuthPostProcessor.class
})
public class JeecgSecurityAutoConfiguration {
    
    // 自动配置在这里完成
    // Shiro的配置通过@Import导入相关配置类
    
}
'''
    
    autoconfigure_path = Path(TARGET_BASE) / "autoconfigure/JeecgSecurityAutoConfiguration.java"
    autoconfigure_path.write_text(autoconfigure_content, encoding='utf-8')
    print(f"   ✓ JeecgSecurityAutoConfiguration.java")

def create_spring_factories():
    """创建spring.factories文件"""
    print("\n🔧 创建spring.factories...")
    
    resources_dir = Path("jeecg-boot-starter-security/src/main/resources/META-INF")
    resources_dir.mkdir(parents=True, exist_ok=True)
    
    factories_content = '''# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\\
org.jeecg.autoconfigure.JeecgSecurityAutoConfiguration
'''
    
    factories_path = resources_dir / "spring.factories"
    factories_path.write_text(factories_content, encoding='utf-8')
    print(f"   ✓ spring.factories")

def create_properties_class():
    """创建配置属性类"""
    print("\n🔧 创建配置属性类...")
    
    properties_content = '''package org.jeecg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jeecg Security 配置属性
 * 
 * @author jeecg-boot
 */
@Data
@ConfigurationProperties(prefix = "jeecg.security")
public class JeecgSecurityProperties {
    
    /**
     * 是否启用安全认证
     */
    private boolean enabled = true;
    
    /**
     * 排除的URL列表(逗号分隔)
     */
    private String excludeUrls = "";
    
    /**
     * JWT配置
     */
    private JwtProperties jwt = new JwtProperties();
    
    @Data
    public static class JwtProperties {
        /**
         * JWT密钥
         */
        private String secret = "jiangbo-secret-key";
        
        /**
         * JWT过期时间(秒)
         */
        private long expire = 7200;
    }
}
'''
    
    properties_path = Path(TARGET_BASE) / "config/JeecgSecurityProperties.java"
    properties_path.write_text(properties_content, encoding='utf-8')
    print(f"   ✓ JeecgSecurityProperties.java")

def main():
    """主函数"""
    print("=" * 60)
    print("🚀 开始迁移 jeecg-boot-starter-security 模块")
    print("=" * 60)
    
    # 1. 创建目录结构
    create_directory_structure()
    
    # 2. 复制文件
    copied_count, failed_files = copy_files()
    
    # 3. 创建自动配置
    create_autoconfiguration()
    
    # 4. 创建spring.factories
    create_spring_factories()
    
    # 5. 创建配置属性类
    create_properties_class()
    
    # 总结
    print("\n" + "=" * 60)
    print("📊 迁移总结")
    print("=" * 60)
    print(f"✓ 成功复制文件: {copied_count}/{len(FILE_MAPPINGS)}")
    
    if failed_files:
        print(f"\n⚠ 失败文件列表:")
        for file in failed_files:
            print(f"   - {file}")
    
    print("\n✅ 迁移完成!")
    print("\n📌 下一步:")
    print("   1. 检查复制的文件是否有编译错误")
    print("   2. 运行: mvn clean compile -DskipTests")
    print("   3. 修复依赖问题")
    print("=" * 60)

if __name__ == "__main__":
    main()