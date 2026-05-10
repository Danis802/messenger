package com.example.messenger.controllers;

import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.serveces.MessageService;
import com.example.messenger.serveces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private MessageService messageService;

    @PutMapping("/addMes")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO mes){
        MessageDTO savedMes = messageService.createMessage(mes);
        return new ResponseEntity<>(savedMes, HttpStatus.CREATED);
    }
}
