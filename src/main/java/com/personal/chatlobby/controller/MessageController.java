package com.personal.chatlobby.controller;

import com.personal.chatlobby.entity.ChatRoom;
import com.personal.chatlobby.entity.Message;
import com.personal.chatlobby.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/room/{room}")
    public List<Message> getMessagesByRoom(@PathVariable ChatRoom room) {
        return messageService.getMessagesByRoom(room);
    }

    @PostMapping
    public Message createMessage(@Valid @RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @GetMapping("/by-sender")
    public List<Message> getBySender(@RequestParam String sender) {
        return messageService.getMessagesBySender(sender);
    }
}
