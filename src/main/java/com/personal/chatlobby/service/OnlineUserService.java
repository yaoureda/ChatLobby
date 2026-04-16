package com.personal.chatlobby.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OnlineUserService {

    private final Map<String, Integer> onlineUsers = new ConcurrentHashMap<>();

    public void userConnected(String username) {
        if (!onlineUsers.containsKey(username)) {
            onlineUsers.put(username, 1);
        } else {
            onlineUsers.put(username, onlineUsers.get(username) + 1);
        }
    }

    public void userDisconnected(String username) {
        onlineUsers.computeIfPresent(username, (key, count) ->
                count > 1 ? count - 1 : null
        );
    }

    public int getOnlineCount() {
        return onlineUsers.size();
    }
}