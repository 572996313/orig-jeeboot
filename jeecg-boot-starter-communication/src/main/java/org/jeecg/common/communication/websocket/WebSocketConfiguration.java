package org.jeecg.common.communication.websocket;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.config.JeecgCommunicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket 配置（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Slf4j
@Configuration
@EnableWebSocket
@ConditionalOnProperty(prefix = "jeecg.communication.websocket", name = "enabled", havingValue = "true")
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired
    private JeecgCommunicationProperties properties;

    @Autowired
    private JeecgWebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("========== 注册WebSocket处理器（简化版） ==========");
        
        String endpoint = properties.getWebsocket().getEndpoint();
        String allowedOrigins = properties.getWebsocket().getAllowedOrigins();
        
        log.info("WebSocket端点: {}", endpoint);
        log.info("允许的源: {}", allowedOrigins);
        
        registry.addHandler(webSocketHandler, endpoint)
                .setAllowedOrigins(allowedOrigins);
    }
}