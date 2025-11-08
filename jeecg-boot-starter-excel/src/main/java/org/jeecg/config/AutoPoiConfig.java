package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * AutoPoi 配置类（向后兼容）
 * 
 * @deprecated 请使用新的配置格式 jeecg.excel.*
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
@Deprecated
@Configuration
@ConditionalOnProperty(name = "jeecg.autopoi.enable")
public class AutoPoiConfig {

    public AutoPoiConfig() {
        log.warn("====================================");
        log.warn("⚠️  检测到旧版 AutoPoi 配置");
        log.warn("⚠️  配置项: jeecg.autopoi.enable");
        log.warn("⚠️  该配置方式已过时，建议迁移到新格式：");
        log.warn("");
        log.warn("新配置格式:");
        log.warn("jeecg:");
        log.warn("  excel:");
        log.warn("    enabled: true");
        log.warn("    engine: easypoi");
        log.warn("    import-config:");
        log.warn("      max-rows: 10000");
        log.warn("    export-config:");
        log.warn("      max-rows: 100000");
        log.warn("");
        log.warn("详细文档: http://doc.jeecg.com/excel");
        log.warn("====================================");
    }
}