package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * Jeecg API文档自动配置类（简化版）
 * 
 * <p>当前版本只加载配置属性和 Swagger3Config，
 * 完整的 Knife4j 配置将在 Phase 20 恢复。</p>
 * 
 * @author llllxf
 * @since 4.0.0
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(JeecgApiDocProperties.class)
@ConditionalOnProperty(prefix = "jeecg.api-doc", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import(Swagger3Config.class)
public class JeecgApiDocAutoConfiguration {
    
    private final JeecgApiDocProperties apiDocProperties;
    
    public JeecgApiDocAutoConfiguration(JeecgApiDocProperties apiDocProperties) {
        this.apiDocProperties = apiDocProperties;
        log.info("=============== Jeecg API文档 自动配置初始化 (简化版) ===============");
        log.info("API文档类型: {}", apiDocProperties.getType());
        log.info("API标题: {}", apiDocProperties.getTitle());
        log.info("API版本: {}", apiDocProperties.getVersion());
        log.info("扫描包路径: {}", apiDocProperties.getBasePackage());
        log.info("生产环境启用: {}", apiDocProperties.getProduction());
        log.info("注意: 完整的 Knife4j 配置将在 Phase 20 恢复");
        log.info("=================================================================");
    }
}