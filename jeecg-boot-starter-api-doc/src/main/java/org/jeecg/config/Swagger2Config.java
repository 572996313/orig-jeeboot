package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger2配置（向后兼容）
 * 
 * @deprecated 请使用 Knife4j 或 SpringDoc
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
@Deprecated
@Configuration
@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "true")
public class Swagger2Config {

    public Swagger2Config() {
        log.warn("Swagger2Config 已过时，请使用 jeecg.api-doc 配置");
        log.warn("建议迁移配置：");
        log.warn("  旧配置: swagger.enable=true");
        log.warn("  新配置: jeecg.api-doc.enabled=true");
    }
}