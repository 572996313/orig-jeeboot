package org.jeecg.config.security;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.config.shiro.ShiroConfig;
import org.jeecg.config.shiro.ShiroRealm;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Jeecg Security 自动配置类
 * <p>
 * 提供 Shiro + JWT 安全认证的自动配置
 * 
 * @author llllxf
 * @date 2025-11-08
 */
@Slf4j
@AutoConfiguration
@ConditionalOnProperty(
    prefix = "jeecg.security", 
    name = "enabled", 
    havingValue = "true", 
    matchIfMissing = true
)
@EnableConfigurationProperties(JeecgSecurityProperties.class)
@Import({ShiroConfig.class})
public class JeecgSecurityAutoConfiguration {

    public JeecgSecurityAutoConfiguration() {
        log.info("===============================================");
        log.info("🔐 Jeecg Security 自动配置已启用");
        log.info("📦 模块: jeecg-boot-starter-security");
        log.info("🔧 功能: Shiro + JWT 安全认证");
        log.info("===============================================");
    }

    /**
     * 创建 ShiroRealm Bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ShiroRealm shiroRealm() {
        log.info("✅ 创建 ShiroRealm Bean");
        return new ShiroRealm();
    }
}