package org.jeecg.common.communication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JeecgBoot 通信自动配置（简化版）
 * 
 * @author jeecg-boot
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(JeecgCommunicationProperties.class)
@ConditionalOnProperty(prefix = "jeecg.communication", name = "enabled", havingValue = "true", matchIfMissing = true)
public class JeecgCommunicationAutoConfiguration {

    public JeecgCommunicationAutoConfiguration() {
        log.info("========== JeecgBoot Communication Starter 初始化完成（简化版） ==========");
    }

    /**
     * 邮件配置（占位符）
     */
    @Configuration
    @ConditionalOnProperty(prefix = "jeecg.communication.email", name = "enabled", havingValue = "true")
    public static class EmailAutoConfiguration {
        
        public EmailAutoConfiguration() {
            log.info("========== 邮件服务已启用（简化版占位符） ==========");
        }
    }

    /**
     * 短信配置（占位符）
     */
    @Configuration
    @ConditionalOnProperty(prefix = "jeecg.communication.sms", name = "enabled", havingValue = "true")
    public static class SmsAutoConfiguration {
        
        public SmsAutoConfiguration() {
            log.info("========== 短信服务已启用（简化版占位符） ==========");
        }
    }

    /**
     * WebSocket配置（占位符）
     */
    @Configuration
    @ConditionalOnProperty(prefix = "jeecg.communication.websocket", name = "enabled", havingValue = "true")
    public static class WebSocketAutoConfiguration {
        
        public WebSocketAutoConfiguration() {
            log.info("========== WebSocket服务已启用（简化版占位符） ==========");
        }
    }
}