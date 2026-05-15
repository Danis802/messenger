package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.mapper.ChatMapper;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatService {
    private ChatRepository chatRepository;
    private SessionRepository sessionRepository;

    public ChatDTO createChat(ChatDTO chatDTO){
        if (sessionRepository.findBySession(chatDTO.getSession()).isPresent()){
            if (sessionRepository.findByLogin(chatDTO.getMemberLogin()).isPresent()){
                Chat chat = ChatMapper.mapToJPA(chatDTO);
                Chat savedChat = chatRepository.save(chat);
                return ChatMapper.mapToDTO(savedChat);
            }else{
                throw new RuntimeException("Member does not exist");
            }
        }else{
            throw new RuntimeException("User is not logged in");
        }

    }
}
