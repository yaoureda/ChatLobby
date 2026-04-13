package com.personal.chatlobby.dto;

import com.personal.chatlobby.entity.ChatRoom;

public class ChatMessage {

    private String content;
    private ChatRoom room;

    public ChatMessage() {
    }

    public ChatMessage(String content, ChatRoom room) {
        this.content = content;
        this.room = room;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatRoom getRoom() {
        return room;
    }

    public void setRoom(ChatRoom room) {
        this.room = room;
    }
}
