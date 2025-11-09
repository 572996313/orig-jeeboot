package org.jeecg.common.communication.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket 处理器（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Slf4j
@Component
public class JeecgWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("========== WebSocket连接建立（简化版） ==========");
        log.info("会话ID: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("========== 接收WebSocket消息（简化版） ==========");
        log.info("会话ID: {}, 消息: {}", session.getId(), message.getPayload());
        
        // 简化版：回显消息
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("========== WebSocket连接关闭（简化版） ==========");
        log.info("会话ID: {}, 状态: {}", session.getId(), status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("========== WebSocket传输错误（简化版） ==========", exception);
        log.error("会话ID: {}", session.getId());
    }
}