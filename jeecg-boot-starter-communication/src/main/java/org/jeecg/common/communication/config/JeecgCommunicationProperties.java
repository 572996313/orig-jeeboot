package org.jeecg.common.communication.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 通信服务配置属性
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.communication")
public class JeecgCommunicationProperties {

    /**
     * 是否启用通信服务
     */
    private Boolean enabled = true;

    /**
     * 邮件配置
     */
    private EmailConfig email = new EmailConfig();

    /**
     * 短信配置
     */
    private SmsConfig sms = new SmsConfig();

    /**
     * WebSocket配置
     */
    private WebSocketConfig websocket = new WebSocketConfig();

    /**
     * 邮件配置
     */
    @Data
    public static class EmailConfig {

        /**
         * 是否启用邮件服务
         */
        private Boolean enabled = false;

        /**
         * 发件人邮箱
         */
        private String from;

        /**
         * 发件人名称
         */
        private String fromName = "JeecgBoot";

        /**
         * 邮件模板路径
         */
        private String templatePath = "classpath:/templates/email/";

        /**
         * 默认模板
         */
        private String defaultTemplate = "default.ftl";

        /**
         * 是否启用HTML格式
         */
        private Boolean html = true;

        /**
         * 邮件编码
         */
        private String encoding = "UTF-8";

        /**
         * 连接超时时间(ms)
         */
        private Integer connectionTimeout = 5000;

        /**
         * 超时时间(ms)
         */
        private Integer timeout = 10000;

        /**
         * 写入超时时间(ms)
         */
        private Integer writeTimeout = 10000;
    }

    /**
     * 短信配置
     */
    @Data
    public static class SmsConfig {

        /**
         * 是否启用短信服务
         */
        private Boolean enabled = false;

        /**
         * 短信服务商: aliyun | tencent
         */
        private String provider = "aliyun";

        /**
         * AccessKey
         */
        private String accessKey;

        /**
         * SecretKey
         */
        private String secretKey;

        /**
         * 签名名称
         */
        private String signName;

        /**
         * 区域ID（阿里云）
         */
        private String regionId = "cn-hangzhou";

        /**
         * 产品名称（阿里云）
         */
        private String product = "Dysmsapi";

        /**
         * 产品域名（阿里云）
         */
        private String domain = "dysmsapi.aliyuncs.com";

        /**
         * SDK AppId（腾讯云）
         */
        private String sdkAppId;

        /**
         * 短信限流配置
         */
        private RateLimitConfig rateLimit = new RateLimitConfig();

        /**
         * 短信模板配置
         */
        private TemplateConfig template = new TemplateConfig();

        /**
         * 短信限流配置
         */
        @Data
        public static class RateLimitConfig {

            /**
             * 是否启用限流
             */
            private Boolean enabled = true;

            /**
             * 同一手机号每天最大发送次数
             */
            private Integer maxPerDay = 10;

            /**
             * 同一手机号每小时最大发送次数
             */
            private Integer maxPerHour = 5;

            /**
             * 同一手机号发送间隔(秒)
             */
            private Integer interval = 60;

            /**
             * 同一IP每小时最大发送次数
             */
            private Integer maxPerIpPerHour = 50;
        }

        /**
         * 短信模板配置
         */
        @Data
        public static class TemplateConfig {

            /**
             * 验证码模板ID
             */
            private String verifyCode;

            /**
             * 登录通知模板ID
             */
            private String loginNotice;

            /**
             * 注册通知模板ID
             */
            private String registerNotice;

            /**
             * 密码重置模板ID
             */
            private String passwordReset;

            /**
             * 订单通知模板ID
             */
            private String orderNotice;

            /**
             * 系统通知模板ID
             */
            private String systemNotice;
        }
    }

    /**
     * WebSocket配置
     */
    @Data
    public static class WebSocketConfig {

        /**
         * 是否启用WebSocket
         */
        private Boolean enabled = false;

        /**
         * WebSocket路径
         */
        private String path = "/websocket/{userId}";

        /**
         * 允许的源
         */
        private String[] allowedOrigins = {"*"};

        /**
         * 是否支持部分消息
         */
        private Boolean allowPartialMessages = false;

        /**
         * 缓冲区大小
         */
        private Integer bufferSize = 8192;

        /**
         * 最大文本消息大小
         */
        private Integer maxTextMessageSize = 64 * 1024;

        /**
         * 最大二进制消息大小
         */
        private Integer maxBinaryMessageSize = 64 * 1024;

        /**
         * 最大会话空闲时间(ms)
         */
        private Long maxSessionIdleTimeout = 5 * 60 * 1000L;

        /**
         * 心跳配置
         */
        private HeartbeatConfig heartbeat = new HeartbeatConfig();

        /**
         * 心跳配置
         */
        @Data
        public static class HeartbeatConfig {

            /**
             * 是否启用心跳
             */
            private Boolean enabled = true;

            /**
             * 心跳间隔(秒)
             */
            private Integer interval = 30;

            /**
             * 心跳超时次数
             */
            private Integer timeoutCount = 3;
        }
    }
}