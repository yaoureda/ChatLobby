package com.personal.chatlobby;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final MessageService messageService;

    public ChatWebSocketController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public Message sendMessage(ChatMessage chatMessage) {
        Message message = new Message(chatMessage.getSender(), chatMessage.getContent());
        return messageService.addMessage(message);
    }
}
