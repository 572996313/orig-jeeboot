package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.aspect.SensitiveDataAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据脱敏自动配置类
 * @author jeecg
 * @date 2024
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "jeecg.desensitization", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DesensitizationAutoConfiguration {

    @Bean
    public SensitiveDataAspect sensitiveDataAspect() {
        log.info("初始化数据脱敏切面...");
        return new SensitiveDataAspect();
    }
}