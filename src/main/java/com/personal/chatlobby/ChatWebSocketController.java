package com.personal.chatlobby;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

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
    public void sendMessage(ChatMessage chatMessage) {
        Message message = new Message(
                chatMessage.getSender(),
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