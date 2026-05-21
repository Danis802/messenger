package com.example.messenger.controllers;

import com.example.messenger.dto.UserDTO;
import com.example.messenger.serveces.SessionService;
import com.example.messenger.serveces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserController(@Autowired UserService userService, @Autowired SessionService sessionService){
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @PutMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO user){
        userService.createUser(user);
        String session = sessionService.addSession(user.getLogin());
        UserDTO savedUser = new UserDTO(user.getLogin(), user.getName(), null, session);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Successful registration",
                        savedUser
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> authorise(@RequestBody UserDTO user){
        UserDTO loggedUser = userService.auth(user);
        return  new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }
}
