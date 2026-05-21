package com.example.messenger.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Пользователь не найден");
    }
}
