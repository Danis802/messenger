package com.example.messenger.controllers;

import com.example.messenger.dto.UserDTO;
import com.example.messenger.serveces.SessionService;
import com.example.messenger.serveces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    private SessionService sessionService;

    @PutMapping("/addUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        userService.createUser(user);
        String session = sessionService.addSession(user.getLogin());
        UserDTO savedUser = new UserDTO(user.getLogin(), null, null, session);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public String authorise(@RequestBody UserDTO user){
//        if (userService.isUser(user)){
//            return sessionService.getSession(user.getLogin());
//        }
//    }
}
