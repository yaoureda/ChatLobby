package com.personal.chatlobby.controller;

import com.personal.chatlobby.service.OnlineUserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class OnlineCountController {

    private final OnlineUserService onlineUserService;

    public OnlineCountController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @MessageMapping("/online-count")
    @SendTo("/topic/online-count")
    public int getOnlineCount() {
        return onlineUserService.getOnlineCount();
    }
}
