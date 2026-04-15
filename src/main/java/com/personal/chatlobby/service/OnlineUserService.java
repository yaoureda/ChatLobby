package com.personal.chatlobby.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OnlineUserService {

    private final Set<String> onlineUsers = new HashSet<>();

    public synchronized void userConnected(String username) {
        onlineUsers.add(username);
    }

    public synchronized void userDisconnected(String username) {
        onlineUsers.remove(username);
    }

    public synchronized int getOnlineCount() {
        return onlineUsers.size();
    }
}
