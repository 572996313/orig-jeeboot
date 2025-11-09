package org.jeecg.common.communication.websocket;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.config.JeecgCommunicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * WebSocket配置类
 * 
 * 配置WebSocket端点和拦截器
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
@Configuration
@EnableWebSocket
@ConditionalOnProperty(prefix = "jeecg.communication.websocket", name = "enabled", havingValue = "true")
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired
    private JeecgWebSocketHandler webSocketHandler;

    @Autowired
    private JeecgCommunicationProperties properties;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        String path = properties.getWebsocket().getPath();
        String[] allowedOrigins = properties.getWebsocket().getAllowedOrigins().split(",");

        registry.addHandler(webSocketHandler, path)
                .setAllowedOrigins(allowedOrigins)
                .addInterceptors(new HttpSessionHandshakeInterceptor());

        log.info("WebSocket已注册，路径: {}, 允许的来源: {}", path, properties.getWebsocket().getAllowedOrigins());
    }
}