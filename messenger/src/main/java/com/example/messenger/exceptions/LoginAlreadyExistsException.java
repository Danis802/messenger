package com.example.messenger.exceptions;

public class LoginAlreadyExistsException extends RuntimeException{
    public LoginAlreadyExistsException() {
        super("Этот логин занят");
    }
}
