package com.jio.callvision.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class CallVisionWebSocketHandler extends TextWebSocketHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(CallVisionWebSocketHandler.class);
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("🔗 WebSocket connected! Session ID: {}", session.getId());
        session.sendMessage(new TextMessage("WebSocket Connected"));
    }
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("📩 Received message: {}", message.getPayload());
        session.sendMessage(new TextMessage("Connected: " + message.getPayload()));
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        logger.info("🔌 WebSocket disconnected! Session ID: {}, Status: {}", session.getId(), status);
    }
}