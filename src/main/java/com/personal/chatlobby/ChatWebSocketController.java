package com.personal.chatlobby;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;

@Controller
public class ChatWebSocketController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(MessageService messageService,
                                   SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage chatMessage, Authentication authentication) {
        String username = authentication.getName();
        Message message = new Message(
                username,
                chatMessage.getContent(),
                chatMessage.getRoom()
        );

        Message savedMessage = messageService.addMessage(message);

        messagingTemplate.convertAndSend(
                "/topic/rooms/" + savedMessage.getRoom(),
                savedMessage
        );
    }
}