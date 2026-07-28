package com.jio.callvision.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CallVisionWebSocketHandler extends TextWebSocketHandler {

    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("WebSocket client connected: " + session.getId());
        session.sendMessage(new TextMessage("{\"type\":\"connected\",\"message\":\"WebSocket Connected Successfully\"}"));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("WebSocket message received: " + message.getPayload());
        // Echo back to client
        session.sendMessage(new TextMessage("{\"type\":\"echo\",\"message\":\"" + message.getPayload() + "\"}"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("WebSocket client disconnected: " + session.getId());
    }

    public static void broadcastIncomingCall(String callerInfo) throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage("{\"type\":\"incoming_call\",\"caller\":\"" + callerInfo + "\"}"));
            }
        }
    }
}
