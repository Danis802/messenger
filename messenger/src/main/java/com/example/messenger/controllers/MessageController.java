package com.example.messenger.controllers;

import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.serveces.ChatService;
import com.example.messenger.serveces.MessageService;
import com.example.messenger.serveces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private MessageService messageService;
    public MessageController(@Autowired MessageService messageService){this.messageService = messageService;}

    @PutMapping("/chat/{id}")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO mes, @PathVariable Long id){
        MessageDTO savedMes = messageService.createMessage(mes, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
