package org.jeecg.config.web;

import org.jeecg.common.aspect.AutoLogAspect;
import org.jeecg.common.aspect.DictAspect;
import org.jeecg.common.aspect.PermissionDataAspect;
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
    UndertowCustomizer.class,
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
                        .allowedOrigins(cors.getAllowedOrigins().split(","))
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

    /**
     * 自动日志切面
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "jeecg.web.log", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "org.aspectj.lang.annotation.Aspect")
    public AutoLogAspect autoLogAspect() {
        log.info("注册自动日志切面");
        return new AutoLogAspect(properties.getLog());
    }

    /**
     * 字典翻译切面
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "jeecg.web.dict", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "org.aspectj.lang.annotation.Aspect")
    public DictAspect dictAspect() {
        log.info("注册字典翻译切面");
        return new DictAspect(properties.getDict());
    }

    /**
     * 数据权限切面
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "jeecg.web.permission", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "org.aspectj.lang.annotation.Aspect")
    public PermissionDataAspect permissionDataAspect() {
        log.info("注册数据权限切面");
        return new PermissionDataAspect(properties.getPermission());
    }
}