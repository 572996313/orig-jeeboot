package org.jeecg.common.communication.config;

import com.aliyuncs.IAcsClient;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.service.EmailService;
import org.jeecg.common.communication.service.SmsService;
import org.jeecg.common.communication.service.impl.AliyunSmsServiceImpl;
import org.jeecg.common.communication.service.impl.EmailServiceImpl;
import org.jeecg.common.communication.websocket.JeecgWebSocketHandler;
import org.jeecg.common.communication.websocket.WebSocketConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.socket.WebSocketHandler;

/**
 * JeecgBoot通信模块自动配置类
 * 
 * 自动配置：
 * 1. 邮件服务
 * 2. 短信服务（阿里云）
 * 3. WebSocket服务
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(JeecgCommunicationProperties.class)
public class JeecgCommunicationAutoConfiguration {

    /**
     * 邮件服务自动配置
     */
    @Configuration
    @ConditionalOnClass(JavaMailSender.class)
    @ConditionalOnProperty(prefix = "jeecg.communication.email", name = "enabled", havingValue = "true")
    static class EmailAutoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public EmailService emailService() {
            log.info("初始化邮件服务");
            return new EmailServiceImpl();
        }

        @Bean
        @ConditionalOnClass(Configuration.class)
        @ConditionalOnMissingBean(name = "emailFreemarkerConfig")
        public Configuration emailFreemarkerConfig(JeecgCommunicationProperties properties) {
            try {
                Configuration config = new Configuration(Configuration.VERSION_2_3_31);
                config.setClassForTemplateLoading(
                    this.getClass(),
                    properties.getEmail().getTemplatePath()
                );
                config.setDefaultEncoding(properties.getEmail().getEncoding());
                log.info("初始化邮件模板引擎，模板路径: {}", properties.getEmail().getTemplatePath());
                return config;
            } catch (Exception e) {
                log.error("初始化邮件模板引擎失败", e);
                return null;
            }
        }
    }

    /**
     * 短信服务自动配置
     */
    @Configuration
    @ConditionalOnClass(IAcsClient.class)
    @ConditionalOnProperty(prefix = "jeecg.communication.sms", name = "enabled", havingValue = "true")
    static class SmsAutoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnProperty(prefix = "jeecg.communication.sms", name = "provider", havingValue = "aliyun")
        public SmsService aliyunSmsService() {
            log.info("初始化阿里云短信服务");
            return new AliyunSmsServiceImpl();
        }

        // 可以在这里添加其他短信服务商的实现
        // 例如：腾讯云短信服务
        /*
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnProperty(prefix = "jeecg.communication.sms", name = "provider", havingValue = "tencent")
        public SmsService tencentSmsService() {
            log.info("初始化腾讯云短信服务");
            return new TencentSmsServiceImpl();
        }
        */
    }

    /**
     * WebSocket服务自动配置
     */
    @Configuration
    @ConditionalOnClass(WebSocketHandler.class)
    @ConditionalOnProperty(prefix = "jeecg.communication.websocket", name = "enabled", havingValue = "true")
    @Import(WebSocketConfiguration.class)
    static class WebSocketAutoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public JeecgWebSocketHandler jeecgWebSocketHandler() {
            log.info("初始化WebSocket处理器");
            return new JeecgWebSocketHandler();
        }
    }

    /**
     * 打印配置信息
     */
    @Bean
    public Object communicationConfigLogger(JeecgCommunicationProperties properties) {
        log.info("========================================");
        log.info("JeecgBoot通信模块配置:");
        log.info("  邮件服务: {}", properties.getEmail().getEnabled() ? "已启用" : "已禁用");
        if (properties.getEmail().getEnabled()) {
            log.info("    发件人: {}", properties.getEmail().getFrom());
            log.info("    发件人名称: {}", properties.getEmail().getFromName());
        }
        log.info("  短信服务: {}", properties.getSms().getEnabled() ? "已启用" : "已禁用");
        if (properties.getSms().getEnabled()) {
            log.info("    服务商: {}", properties.getSms().getProvider());
            log.info("    签名: {}", properties.getSms().getSignName());
            log.info("    限流配置: 每日{}条/每小时{}条/间隔{}秒",
                properties.getSms().getRateLimit().getMaxPerDay(),
                properties.getSms().getRateLimit().getMaxPerHour(),
                properties.getSms().getRateLimit().getInterval());
        }
        log.info("  WebSocket服务: {}", properties.getWebsocket().getEnabled() ? "已启用" : "已禁用");
        if (properties.getWebsocket().getEnabled()) {
            log.info("    路径: {}", properties.getWebsocket().getPath());
            log.info("    心跳检测: {}", properties.getWebsocket().getHeartbeat().getEnabled() ? "已启用" : "已禁用");
            if (properties.getWebsocket().getHeartbeat().getEnabled()) {
                log.info("      心跳间隔: {}秒", properties.getWebsocket().getHeartbeat().getInterval());
                log.info("      超时次数: {}", properties.getWebsocket().getHeartbeat().getTimeoutCount());
            }
        }
        log.info("========================================");
        return null;
    }
}