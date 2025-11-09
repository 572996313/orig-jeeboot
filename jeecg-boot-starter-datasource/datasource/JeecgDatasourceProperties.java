package org.jeecg.config.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jeecg数据源配置属性
 * 
 * @author llllxf
 * @version 1.0
 * @since 2025-11-08
 */
@Data
@ConfigurationProperties(prefix = "jeecg.datasource")
public class JeecgDatasourceProperties {

    /**
     * 是否启用数据源配置
     */
    private boolean enable = true;

    /**
     * Druid配置
     */
    private DruidProperties druid = new DruidProperties();

    /**
     * 动态数据源配置
     */
    private DynamicProperties dynamic = new DynamicProperties();

    /**
     * CORS跨域配置
     */
    private CorsProperties cors = new CorsProperties();

    /**
     * Druid配置属性
     */
    @Data
    public static class DruidProperties {
        /**
         * 是否启用Druid监控
         */
        private boolean statViewServlet = true;

        /**
         * 监控页面访问路径
         */
        private String statViewServletUrlPattern = "/druid/*";

        /**
         * 监控页面登录用户名
         */
        private String statViewServletLoginUsername = "admin";

        /**
         * 监控页面登录密码
         */
        private String statViewServletLoginPassword = "123456";

        /**
         * 是否启用Web监控
         */
        private boolean webStatFilter = true;

        /**
         * Web监控排除路径
         */
        private String webStatFilterExclusions = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";

        /**
         * 是否启用防火墙
         */
        private boolean wallEnabled = true;

        /**
         * 是否允许多语句执行
         */
        private boolean multiStatementAllow = true;
    }

    /**
     * 动态数据源配置属性
     */
    @Data
    public static class DynamicProperties {
        /**
         * 是否启用动态数据源
         */
        private boolean enable = false;

        /**
         * 是否启用缓存
         */
        private boolean cache = true;

        /**
         * 缓存过期时间（分钟）
         */
        private int cacheExpireMinutes = 30;
    }

    /**
     * CORS跨域配置属性
     */
    @Data
    public static class CorsProperties {
        /**
         * 是否启用CORS
         */
        private boolean enable = true;

        /**
         * 允许的源
         */
        private String allowedOrigins = "*";

        /**
         * 允许的方法
         */
        private String allowedMethods = "GET,POST,PUT,DELETE,OPTIONS";

        /**
         * 允许的头
         */
        private String allowedHeaders = "*";

        /**
         * 是否允许携带凭证
         */
        private boolean allowCredentials = true;

        /**
         * 预检请求缓存时间（秒）
         */
        private long maxAge = 3600;
    }
}