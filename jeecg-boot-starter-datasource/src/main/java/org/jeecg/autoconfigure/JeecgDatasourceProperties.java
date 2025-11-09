package org.jeecg.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jeecg数据源配置属性
 *
 * @author jeecg
 */
@Data
@ConfigurationProperties(prefix = "jeecg.datasource")
public class JeecgDatasourceProperties {

    /**
     * 是否启用数据源配置
     */
    private boolean enable = true;

    /**
     * Druid数据源配置
     */
    private DruidProperties druid = new DruidProperties();

    /**
     * 动态数据源配置
     */
    private DynamicProperties dynamic = new DynamicProperties();

    @Data
    public static class DruidProperties {
        /**
         * 是否启用Druid监控
         */
        private boolean enable = true;
    }

    @Data
    public static class DynamicProperties {
        /**
         * 是否启用动态数据源
         */
        private boolean enable = false;
    }
}