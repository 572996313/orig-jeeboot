package org.jeecg.common.communication.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JeecgBoot 通信配置属性（简化版）
 * 
 * @author jeecg-boot
 */
@Data
@ConfigurationProperties(prefix = "jeecg.communication")
public class JeecgCommunicationProperties {

    /**
     * 是否启用通信模块
     */
    private boolean enabled = true;

    /**
     * 邮件配置（占位符）
     */
    private EmailConfig email = new EmailConfig();

    /**
     * 短信配置（占位符）
     */
    private SmsConfig sms = new SmsConfig();

    /**
     * WebSocket配置（占位符）
     */
    private WebSocketConfig websocket = new WebSocketConfig();

    @Data
    public static class EmailConfig {
        private boolean enabled = false;
        private String from = "noreply@example.com";
        private String templatePath = "classpath:/templates/email/";
    }

    @Data
    public static class SmsConfig {
        private boolean enabled = false;
        private String provider = "aliyun";
        private String accessKey;
        private String secretKey;
        private String signName;
        private RateLimitConfig rateLimit = new RateLimitConfig();

        @Data
        public static class RateLimitConfig {
            private int maxPerDay = 100;
            private int maxPerHour = 10;
            private int interval = 60;
        }
    }

    @Data
    public static class WebSocketConfig {
        private boolean enabled = false;
        private String endpoint = "/websocket";
        private String allowedOrigins = "*";
    }
}