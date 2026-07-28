package com.jio.callvision.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class CallVisionWebSocketHandler extends TextWebSocketHandler {
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Echo the message back to client
        session.sendMessage(new TextMessage("Connected: " + message.getPayload()));
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("WebSocket Connected"));
    }
}