package org.jeecg.config.web;

import org.jeecg.common.exception.JeecgBootExceptionHandler;
import org.jeecg.config.firewall.LowCodeModeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * JeecgBoot Web自动配置
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "jeecg.web", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JeecgWebProperties.class)
@Import({
    WebMvcConfiguration.class,
    LowCodeModeConfiguration.class
})
public class JeecgWebAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JeecgWebAutoConfiguration.class);

    private final JeecgWebProperties properties;

    public JeecgWebAutoConfiguration(JeecgWebProperties properties) {
        this.properties = properties;
        log.info("JeecgBoot Web模块初始化...");
    }

    /**
     * 配置跨域
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.web.cors", name = "enabled", havingValue = "true", matchIfMissing = true)
    public WebMvcConfigurer corsConfigurer() {
        log.info("配置CORS跨域支持");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                JeecgWebProperties.CorsConfig cors = properties.getCors();
                registry.addMapping("/**")
                        .allowedOriginPatterns(cors.getAllowedOrigins().split(","))
                        .allowedMethods(cors.getAllowedMethods().split(","))
                        .allowedHeaders(cors.getAllowedHeaders().split(","))
                        .allowCredentials(cors.isAllowCredentials())
                        .maxAge(cors.getMaxAge());
            }
        };
    }

    /**
     * 全局异常处理器
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = "org.springframework.web.bind.annotation.RestControllerAdvice")
    public JeecgBootExceptionHandler jeecgBootExceptionHandler() {
        log.info("注册全局异常处理器");
        return new JeecgBootExceptionHandler();
    }

    // 注意: AutoLogAspect, DictAspect, PermissionDataAspect 已通过 @Component 自动注册
    // 它们位于 jeecg-boot-base-core 模块中，会被组件扫描自动发现
}