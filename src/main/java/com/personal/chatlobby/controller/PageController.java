package com.personal.chatlobby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "pages/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "pages/register";
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "pages/chat";
    }
}
