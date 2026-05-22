package com.example.messenger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    @GetMapping("/")
    public String renderMain(){return "index";}

    @GetMapping("/register")
    public String renderReg(){return "register";}

    @GetMapping("/login")
    public String renderLog(){return "login";}

    @GetMapping("/chat/{id}")
    public String renderChat(){return "chat";}

    @GetMapping("/chat/add")
    public String renderAddChat(){return "addChat";}
}
