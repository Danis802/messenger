package com.example.messenger.exceptions;

public class UserNotAuthorizedException extends RuntimeException{
    public UserNotAuthorizedException(){
        super("Чтобы продолжить использование, авторизуйтесь");
    }
}
