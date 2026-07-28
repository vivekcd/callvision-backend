package com.jio.callvision.config;

import com.jio.callvision.websocket.CallVisionWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    
    private final CallVisionWebSocketHandler webSocketHandler;
    
    public WebSocketConfig(CallVisionWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/incoming-call")
                .setAllowedOrigins("*");
    }
}