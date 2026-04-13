package com.personal.chatlobby.repository;

import com.personal.chatlobby.entity.ChatRoom;
import com.personal.chatlobby.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(String sender);
    List<Message> findAllByOrderByCreatedAtAsc();
    List<Message> findByRoomOrderByCreatedAtAsc(ChatRoom room);
}
