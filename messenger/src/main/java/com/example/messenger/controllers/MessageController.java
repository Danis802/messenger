package com.example.messenger.controllers;

import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.serveces.ChatService;
import com.example.messenger.serveces.MessageService;
import com.example.messenger.serveces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private MessageService messageService;
    public MessageController(@Autowired MessageService messageService){this.messageService = messageService;}

    @PutMapping("/chat/{id}")
    public ResponseEntity<ApiResponse> createMessage(@RequestBody MessageDTO mes, @PathVariable Long id){
        MessageDTO savedMes = messageService.createMessage(mes, id);
//        return new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Successful sending",
                        null
                )
        );
    }

    @GetMapping("/chat/messages")
    public ResponseEntity<ApiResponse<List<MessageDTO>>> getMessages(@RequestParam Long chatId, @RequestParam String session){
        List<MessageDTO> messages = messageService.getAllMessages(chatId, session);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Successful sending",
                        messages
                )
        );
    }
}
