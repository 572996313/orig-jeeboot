package org.jeecg.common.communication.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.config.JeecgCommunicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket处理器
 * 
 * 功能：
 * 1. 管理WebSocket连接
 * 2. 消息收发处理
 * 3. 心跳保活机制
 * 4. 连接超时检测
 * 5. 广播和单播消息
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
@Component
public class JeecgWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private JeecgCommunicationProperties properties;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 存储所有在线用户的WebSocket会话
     * Key: userId, Value: WebSocketSession
     */
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    /**
     * 记录每个会话的心跳计数
     * Key: sessionId, Value: 心跳计数器
     */
    private final Map<String, AtomicInteger> heartbeatCounters = new ConcurrentHashMap<>();

    /**
     * 心跳检测定时器
     */
    private ScheduledExecutorService heartbeatScheduler;

    /**
     * 连接建立后调用
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        if (userId != null) {
            sessions.put(userId, session);
            heartbeatCounters.put(session.getId(), new AtomicInteger(0));
            log.info("WebSocket连接建立，用户ID: {}, 会话ID: {}", userId, session.getId());

            // 启动心跳检测
            startHeartbeatCheck();

            // 发送欢迎消息
            sendMessage(session, createMessage("CONNECTED", "连接成功"));
        } else {
            log.warn("无法获取用户ID，关闭连接");
            session.close(CloseStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * 收到文本消息时调用
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.debug("收到消息，会话ID: {}, 内容: {}", session.getId(), payload);

        try {
            // 解析消息
            @SuppressWarnings("unchecked")
            Map<String, Object> msg = objectMapper.readValue(payload, Map.class);
            String type = (String) msg.get("type");

            // 处理不同类型的消息
            switch (type) {
                case "PING":
                    // 收到心跳消息，重置计数器
                    heartbeatCounters.get(session.getId()).set(0);
                    sendMessage(session, createMessage("PONG", "心跳响应"));
                    break;

                case "MESSAGE":
                    // 业务消息，转发给目标用户
                    String targetUserId = (String) msg.get("targetUserId");
                    String content = (String) msg.get("content");
                    sendToUser(targetUserId, createMessage("MESSAGE", content));
                    break;

                case "BROADCAST":
                    // 广播消息给所有在线用户
                    String broadcastContent = (String) msg.get("content");
                    broadcast(createMessage("BROADCAST", broadcastContent));
                    break;

                default:
                    log.warn("未知消息类型: {}", type);
                    sendMessage(session, createMessage("ERROR", "未知消息类型"));
            }
        } catch (Exception e) {
            log.error("处理消息失败", e);
            sendMessage(session, createMessage("ERROR", "消息处理失败: " + e.getMessage()));
        }
    }

    /**
     * 连接关闭后调用
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = getUserId(session);
        if (userId != null) {
            sessions.remove(userId);
            heartbeatCounters.remove(session.getId());
            log.info("WebSocket连接关闭，用户ID: {}, 会话ID: {}, 状态: {}", 
                userId, session.getId(), status);
        }

        // 如果没有活跃连接，停止心跳检测
        if (sessions.isEmpty() && heartbeatScheduler != null) {
            heartbeatScheduler.shutdown();
            heartbeatScheduler = null;
        }
    }

    /**
     * 传输错误时调用
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket传输错误，会话ID: {}", session.getId(), exception);
        
        if (session.isOpen()) {
            session.close(CloseStatus.SERVER_ERROR);
        }
    }

    /**
     * 发送消息到指定用户
     * 
     * @param userId 用户ID
     * @param message 消息内容
     * @return 是否发送成功
     */
    public boolean sendToUser(String userId, String message) {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            return sendMessage(session, message);
        }
        log.warn("用户不在线或会话已关闭，用户ID: {}", userId);
        return false;
    }

    /**
     * 广播消息给所有在线用户
     * 
     * @param message 消息内容
     */
    public void broadcast(String message) {
        log.info("广播消息给 {} 个在线用户", sessions.size());
        sessions.values().forEach(session -> {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        });
    }

    /**
     * 获取在线用户数
     * 
     * @return 在线用户数
     */
    public int getOnlineCount() {
        return sessions.size();
    }

    /**
     * 检查用户是否在线
     * 
     * @param userId 用户ID
     * @return 是否在线
     */
    public boolean isOnline(String userId) {
        WebSocketSession session = sessions.get(userId);
        return session != null && session.isOpen();
    }

    /**
     * 发送消息到指定会话
     */
    private boolean sendMessage(WebSocketSession session, String message) {
        try {
            synchronized (session) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                    return true;
                }
            }
        } catch (IOException e) {
            log.error("发送消息失败，会话ID: {}", session.getId(), e);
        }
        return false;
    }

    /**
     * 创建标准格式消息
     */
    private String createMessage(String type, String content) {
        try {
            Map<String, Object> message = new ConcurrentHashMap<>();
            message.put("type", type);
            message.put("content", content);
            message.put("timestamp", System.currentTimeMillis());
            return objectMapper.writeValueAsString(message);
        } catch (Exception e) {
            log.error("创建消息失败", e);
            return "{\"type\":\"ERROR\",\"content\":\"消息创建失败\"}";
        }
    }

    /**
     * 从会话中获取用户ID
     */
    private String getUserId(WebSocketSession session) {
        // 从URI路径中提取用户ID: /websocket/{userId}
        String path = session.getUri().getPath();
        String[] parts = path.split("/");
        return parts.length > 0 ? parts[parts.length - 1] : null;
    }

    /**
     * 启动心跳检测
     */
    private void startHeartbeatCheck() {
        if (!properties.getWebsocket().getHeartbeat().getEnabled()) {
            return;
        }

        if (heartbeatScheduler == null || heartbeatScheduler.isShutdown()) {
            heartbeatScheduler = new ScheduledThreadPoolExecutor(1, r -> {
                Thread thread = new Thread(r, "WebSocket-Heartbeat");
                thread.setDaemon(true);
                return thread;
            });

            long interval = properties.getWebsocket().getHeartbeat().getInterval();
            int timeoutCount = properties.getWebsocket().getHeartbeat().getTimeoutCount();

            heartbeatScheduler.scheduleAtFixedRate(() -> {
                try {
                    checkHeartbeat(timeoutCount);
                } catch (Exception e) {
                    log.error("心跳检测异常", e);
                }
            }, interval, interval, TimeUnit.SECONDS);

            log.info("WebSocket心跳检测已启动，间隔: {}秒, 超时次数: {}", interval, timeoutCount);
        }
    }

    /**
     * 检查心跳超时
     */
    private void checkHeartbeat(int timeoutCount) {
        sessions.forEach((userId, session) -> {
            AtomicInteger counter = heartbeatCounters.get(session.getId());
            if (counter != null) {
                int count = counter.incrementAndGet();
                if (count >= timeoutCount) {
                    log.warn("用户心跳超时，关闭连接，用户ID: {}, 超时次数: {}", userId, count);
                    try {
                        session.close(CloseStatus.GOING_AWAY.withReason("心跳超时"));
                    } catch (IOException e) {
                        log.error("关闭会话失败", e);
                    }
                } else if (count > 0) {
                    log.debug("用户心跳未响应，用户ID: {}, 次数: {}/{}", userId, count, timeoutCount);
                }
            }
        });
    }
}