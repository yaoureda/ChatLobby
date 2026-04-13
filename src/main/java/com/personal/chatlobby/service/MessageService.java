package com.personal.chatlobby.service;

import com.personal.chatlobby.entity.ChatRoom;
import com.personal.chatlobby.entity.Message;
import com.personal.chatlobby.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByCreatedAtAsc();
    }

    public List<Message> getMessagesByRoom(ChatRoom room) {
        return messageRepository.findByRoomOrderByCreatedAtAsc(room);
    }

    public Message addMessage(Message message) {
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(LocalDateTime.now());
        }
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBySender(String sender) {
        return messageRepository.findBySender(sender);
    }
}
