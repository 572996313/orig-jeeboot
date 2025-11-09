package org.jeecg.config.web;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * Undertow服务器定制器
 * 仅在 Undertow 类存在时才会加载此配置
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Undertow.class, UndertowServletWebServerFactory.class})
@ConditionalOnProperty(prefix = "server", name = "servlet.context-path")
public class UndertowCustomizer implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    private static final Logger log = LoggerFactory.getLogger(UndertowCustomizer.class);

    private final JeecgWebProperties properties;

    public UndertowCustomizer(JeecgWebProperties properties) {
        this.properties = properties;
        log.info("Undertow服务器定制器初始化");
    }

    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        JeecgWebProperties.UndertowConfig undertow = properties.getUndertow();
        
        factory.addBuilderCustomizers(builder -> {
            // 设置IO线程数和工作线程数
            builder.setIoThreads(undertow.getIoThreads());
            builder.setWorkerThreads(undertow.getWorkerThreads());
            
            log.info("Undertow配置 - IO线程数: {}, 工作线程数: {}", 
                    undertow.getIoThreads(), undertow.getWorkerThreads());
        });
        
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            // 设置缓冲区大小
            deploymentInfo.setDefaultEncoding("UTF-8");
            log.debug("Undertow部署配置完成");
        });
        
        // 服务器选项配置
        factory.addBuilderCustomizers(builder -> {
            builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
            builder.setServerOption(UndertowOptions.HTTP2_SETTINGS_ENABLE_PUSH, true);
            log.debug("启用HTTP/2支持");
        });
    }
}