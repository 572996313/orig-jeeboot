package org.jeecg.config.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * JeecgBoot Web配置属性
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@Data
@ConfigurationProperties(prefix = "jeecg.web")
public class JeecgWebProperties {

    /**
     * 是否启用Web增强功能
     */
    private boolean enabled = true;

    /**
     * 跨域配置
     */
    private CorsConfig cors = new CorsConfig();

    /**
     * 防火墙配置
     */
    private FirewallConfig firewall = new FirewallConfig();

    /**
     * 日志配置
     */
    private LogConfig log = new LogConfig();

    /**
     * 字典翻译配置
     */
    private DictConfig dict = new DictConfig();

    /**
     * 数据权限配置
     */
    private PermissionConfig permission = new PermissionConfig();

    /**
     * Undertow服务器配置
     */
    private UndertowConfig undertow = new UndertowConfig();

    /**
     * 跨域配置
     */
    @Data
    public static class CorsConfig {
        /**
         * 是否启用跨域
         */
        private boolean enabled = true;

        /**
         * 允许的源
         */
        private String allowedOrigins = "*";

        /**
         * 允许的方法
         */
        private String allowedMethods = "*";

        /**
         * 允许的头部
         */
        private String allowedHeaders = "*";

        /**
         * 是否允许携带凭证
         */
        private boolean allowCredentials = true;

        /**
         * 预检请求的有效期(秒)
         */
        private long maxAge = 3600;
    }

    /**
     * 防火墙配置
     */
    @Data
    public static class FirewallConfig {
        /**
         * 是否启用防火墙
         */
        private boolean enabled = true;

        /**
         * 是否启用低代码模式
         */
        private boolean lowCodeMode = false;

        /**
         * SQL注入检查
         */
        private boolean sqlInjectionCheck = true;

        /**
         * XSS攻击检查
         */
        private boolean xssCheck = true;

        /**
         * 白名单URL列表
         */
        private List<String> whitelistUrls = new ArrayList<>();

        /**
         * 黑名单URL列表
         */
        private List<String> blacklistUrls = new ArrayList<>();
    }

    /**
     * 日志配置
     */
    @Data
    public static class LogConfig {
        /**
         * 是否启用自动日志
         */
        private boolean enabled = true;

        /**
         * 是否记录请求参数
         */
        private boolean logArgs = true;

        /**
         * 是否记录返回结果
         */
        private boolean logResult = true;

        /**
         * 是否记录执行时间
         */
        private boolean logTime = true;

        /**
         * 慢请求阈值(毫秒)
         */
        private long slowRequestThreshold = 3000;

        /**
         * 排除的URL列表
         */
        private List<String> excludeUrls = new ArrayList<>();
    }

    /**
     * 字典翻译配置
     */
    @Data
    public static class DictConfig {
        /**
         * 是否启用字典翻译
         */
        private boolean enabled = true;

        /**
         * 是否异步翻译
         */
        private boolean async = false;

        /**
         * 缓存时间(秒)，0表示不缓存
         */
        private int cacheSeconds = 300;
    }

    /**
     * 数据权限配置
     */
    @Data
    public static class PermissionConfig {
        /**
         * 是否启用数据权限
         */
        private boolean enabled = true;

        /**
         * 是否启用严格模式
         */
        private boolean strict = false;

        /**
         * 排除的表名列表
         */
        private List<String> excludeTables = new ArrayList<>();
    }

    /**
     * Undertow服务器配置
     */
    @Data
    public static class UndertowConfig {
        /**
         * IO线程数
         */
        private int ioThreads = 16;

        /**
         * 工作线程数
         */
        private int workerThreads = 256;

        /**
         * 缓冲区大小(字节)
         */
        private int bufferSize = 1024;

        /**
         * 是否直接缓冲
         */
        private boolean directBuffers = true;
    }
}