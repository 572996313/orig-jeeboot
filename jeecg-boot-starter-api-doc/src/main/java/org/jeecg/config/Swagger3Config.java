package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger3配置（向后兼容）
 * 
 * @deprecated 请使用统一的 jeecg.api-doc 配置
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
@Deprecated
@Configuration
@ConditionalOnProperty(prefix = "knife4j", name = "enable", havingValue = "true")
public class Swagger3Config {

    public Swagger3Config() {
        log.warn("Swagger3Config 已过时，请使用 jeecg.api-doc 配置");
        log.warn("建议迁移配置：");
        log.warn("  旧配置: knife4j.enable=true");
        log.warn("  新配置: jeecg.api-doc.enabled=true");
        log.warn("  新配置: jeecg.api-doc.type=knife4j");
    }
}