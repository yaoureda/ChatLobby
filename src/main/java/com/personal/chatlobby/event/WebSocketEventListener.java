package com.personal.chatlobby.event;

import com.personal.chatlobby.service.OnlineUserService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final OnlineUserService onlineUserService;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(OnlineUserService onlineUserService,
                                  SimpMessagingTemplate messagingTemplate) {
        this.onlineUserService = onlineUserService;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        Object principal = event.getUser();

        if (principal instanceof Authentication authentication) {
            String username = authentication.getName();
            onlineUserService.userConnected(username);
            broadcastOnlineCount();
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        Object principal = event.getUser();

        if (principal instanceof Authentication authentication) {
            String username = authentication.getName();
            onlineUserService.userDisconnected(username);
            broadcastOnlineCount();
        }
    }

    private void broadcastOnlineCount() {
        messagingTemplate.convertAndSend(
                "/topic/online-count",
                onlineUserService.getOnlineCount()
        );
    }
}
