package com.personal.chatlobby;

public class ChatMessage {

    private String content;
    private String room;

    public ChatMessage() {
    }

    public ChatMessage(String content, String room) {
        this.content = content;
        this.room = room;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
