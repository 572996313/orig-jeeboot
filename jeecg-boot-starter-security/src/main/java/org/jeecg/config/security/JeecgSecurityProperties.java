package org.jeecg.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jeecg Security 配置属性
 * 
 * @author llllxf
 * @date 2025-11-08
 */
@Data
@ConfigurationProperties(prefix = "jeecg.security")
public class JeecgSecurityProperties {

    /**
     * 是否启用安全认证
     */
    private boolean enabled = true;

    /**
     * Shiro 配置
     */
    private ShiroProperties shiro = new ShiroProperties();

    /**
     * JWT 配置
     */
    private JwtProperties jwt = new JwtProperties();

    /**
     * Shiro 配置属性
     */
    @Data
    public static class ShiroProperties {
        
        /**
         * 是否启用 Shiro
         */
        private boolean enabled = true;

        /**
         * 排除拦截的 URL，逗号分隔
         */
        private String excludeUrls = "/sys/login,/sys/logout,/sys/cas/client/validateLogin";

        /**
         * 是否启用 URL 权限控制
         */
        private boolean urlPermissionEnabled = false;
    }

    /**
     * JWT 配置属性
     */
    @Data
    public static class JwtProperties {
        
        /**
         * JWT 密钥
         */
        private String secret = "jiangbo-secret-key";

        /**
         * JWT 过期时间（秒），默认 7 天
         */
        private long expireTime = 7 * 24 * 60 * 60;

        /**
         * Token 请求头名称
         */
        private String tokenHeader = "X-Access-Token";
    }
}