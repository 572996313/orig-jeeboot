package org.jeecg.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.config.CorsFilterCondition;
import org.jeecg.config.DruidConfig;
import org.jeecg.config.DruidWallConfigRegister;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;

/**
 * Jeecg数据源自动配置
 *
 * @author jeecg
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(JeecgDatasourceProperties.class)
@ConditionalOnProperty(prefix = "jeecg.datasource", name = "enable", havingValue = "true", matchIfMissing = true)
public class JeecgDatasourceAutoConfiguration {

    public JeecgDatasourceAutoConfiguration() {
        log.info("=== Jeecg Datasource Starter 自动配置已加载 ===");
    }

    /**
     * 导入Druid配置
     */
    @Import({DruidConfig.class, DruidWallConfigRegister.class})
    @ConditionalOnProperty(prefix = "spring.datasource.druid", name = "enable", havingValue = "true", matchIfMissing = true)
    public static class DruidAutoConfiguration {
        public DruidAutoConfiguration() {
            log.info("Druid数据源配置已启用");
        }
    }
}