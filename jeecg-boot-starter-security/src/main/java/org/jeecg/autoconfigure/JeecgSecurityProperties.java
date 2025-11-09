package org.jeecg.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jeecg Security 配置属性
 * 
 * @author jeecg-boot
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.security")
public class JeecgSecurityProperties {
    
    /**
     * 是否启用安全认证
     */
    private boolean enabled = true;
    
    /**
     * 排除的URL列表(逗号分隔)
     */
    private String excludeUrls = "";
    
    /**
     * JWT配置
     */
    private JwtProperties jwt = new JwtProperties();
    
    /**
     * JWT配置属性
     */
    @Data
    public static class JwtProperties {
        /**
         * JWT密钥
         */
        private String secret = "jiangbo-secret-key";
        
        /**
         * JWT过期时间(秒)
         */
        private long expire = 7200;
    }
}