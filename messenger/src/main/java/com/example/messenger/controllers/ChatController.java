package com.example.messenger.controllers;

import com.example.messenger.dto.ChatDTO;
import com.example.messenger.serveces.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {
    private ChatService chatService;

    @PutMapping("/addChat")
    public ResponseEntity<ChatDTO> createChat(@RequestBody ChatDTO chat){
        ChatDTO savedChat = chatService.createChat(chat);
        return new ResponseEntity<>(savedChat, HttpStatus.CREATED);
    }
}
