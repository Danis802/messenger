package com.example.messenger.mapper;

import com.example.messenger.database.Messages;
import com.example.messenger.dto.MessageDTO;

public class MessageMapper {
    public static MessageDTO mapToDTO (Messages mes){
        return new MessageDTO(mes.getId(), mes.getContent(), mes.getSender());
    }

    public static Messages mapToJPA(MessageDTO mes){
        return new Messages(mes.getId(), mes.getContent(), mes.getSender(), mes.getChatId());
    }
}
