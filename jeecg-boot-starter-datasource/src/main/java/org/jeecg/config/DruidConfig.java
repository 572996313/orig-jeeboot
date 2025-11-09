package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Druid数据源配置
 * <p>依赖Druid Spring Boot Starter的自动配置</p>
 * <p>通过application.yml配置：spring.datasource.druid.*</p>
 *
 * @author jeecg
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource.druid", name = "enable", havingValue = "true", matchIfMissing = true)
public class DruidConfig {

    public DruidConfig() {
        log.info("=== Jeecg Druid数据源配置已加载 ===");
        log.info("监控页面访问地址: http://localhost:port/druid/index.html");
        log.info("默认用户名/密码: admin/123456");
    }
}