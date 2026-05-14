package com.example.messenger.mapper;

import com.example.messenger.database.Chat;
import com.example.messenger.dto.ChatDTO;

public class ChatMapper {
    public static ChatDTO mapToDTO (Chat chat){
        return new ChatDTO(chat.getId(), chat.getName(), chat.getCreatorLogin(), chat.getMemberLogin());
    }

    public static Chat mapToJPA(ChatDTO chat){
        return new Chat(chat.getId(), chat.getName(), chat.getCreatorLogin(), chat.getMemberLogin());
    }
}
