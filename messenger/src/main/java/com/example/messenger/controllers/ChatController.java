package com.example.messenger.controllers;

import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.serveces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    private ChatService chatService;
    public ChatController(@Autowired ChatService chatService){this.chatService = chatService;}

    @PutMapping("/addChat")
    public ResponseEntity<ApiResponse<ChatDTO>> createChat(@RequestBody ChatDTO chat){
        ChatDTO savedChat = chatService.createChat(chat);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "",
                        savedChat
                )
        );
    }

    @GetMapping("/chat")
    public ResponseEntity<ApiResponse<List<ChatDTO>>> getChats(@RequestParam String session){
        List<ChatDTO> chats = chatService.getAllChats(session);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "",
                        chats
                )
        );
    }
}
