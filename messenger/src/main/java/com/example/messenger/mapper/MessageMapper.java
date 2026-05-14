package com.example.messenger.mapper;

import com.example.messenger.database.Message;
import com.example.messenger.dto.MessageDTO;

public class MessageMapper {
    public static MessageDTO mapToDTO (Message mes){
        return new MessageDTO(mes.getId(), mes.getContent());
    }

    public static Message mapToJPA(MessageDTO mes){
        return new Message(mes.getId(), mes.getContent(), mes.getSender());
    }
}
