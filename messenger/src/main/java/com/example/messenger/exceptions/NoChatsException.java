package com.example.messenger.exceptions;

public class NoChatsException extends RuntimeException{
    public NoChatsException(){
        super("no chats");
    }
}
