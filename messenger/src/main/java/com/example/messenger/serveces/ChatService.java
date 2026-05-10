package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.mapper.ChatMapper;
import com.example.messenger.repositories.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatService {
    private ChatRepository chatRepository;

    public ChatDTO createChat(ChatDTO chatDTO){
        Chat chat = ChatMapper.mapToJPA(chatDTO);
        Chat savedChat = chatRepository.save(chat);
        return ChatMapper.mapToDTO(savedChat);
    }
}
