package com.personal.chatlobby.controller;

import com.personal.chatlobby.entity.ChatRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {

    @GetMapping("/all")
    public ChatRoom[] getChatRooms() {
        return ChatRoom.values();
    }
}
