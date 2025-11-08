package org.jeecg.config.firewall;

import org.jeecg.config.web.JeecgWebProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 低代码模式/防火墙配置
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@Configuration
@ConditionalOnProperty(prefix = "jeecg.web.firewall", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LowCodeModeConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(LowCodeModeConfiguration.class);

    private final JeecgWebProperties properties;

    public LowCodeModeConfiguration(JeecgWebProperties properties) {
        this.properties = properties;
        log.info("防火墙配置初始化");
    }

    @Bean
    public LowCodeModeInterceptor lowCodeModeInterceptor() {
        return new LowCodeModeInterceptor(properties.getFirewall());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        JeecgWebProperties.FirewallConfig firewall = properties.getFirewall();
        
        if (firewall.isEnabled()) {
            registry.addInterceptor(lowCodeModeInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns(firewall.getWhitelistUrls());
            
            log.info("注册防火墙拦截器 - 低代码模式: {}, SQL注入检查: {}, XSS检查: {}", 
                    firewall.isLowCodeMode(), 
                    firewall.isSqlInjectionCheck(), 
                    firewall.isXssCheck());
        }
    }
}