package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.SessionDTO;
import com.example.messenger.exceptions.NoChatsException;
import com.example.messenger.exceptions.UserNotAuthorizedException;
import com.example.messenger.mapper.ChatMapper;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {
    private ChatRepository chatRepository;
    private SessionRepository sessionRepository;

    public ChatDTO createChat(ChatDTO chatDTO){
        SessionDTO sessionDTO = sessionRepository.findBySession(chatDTO.getSession())
                .orElseThrow(()-> new RuntimeException("User is not logged in"));
        String creatorLogin = sessionDTO.getLogin();
        if (sessionRepository.findByLogin(chatDTO.getMemberLogin()).isPresent()){
            chatDTO.setCreatorLogin(creatorLogin);
            Chat chat = ChatMapper.mapToJPA(chatDTO);
            Chat savedChat = chatRepository.save(chat);
            return ChatMapper.mapToDTO(savedChat);
        }else{
            throw new RuntimeException("Member does not exist");
        }

    }

    public List<ChatDTO> getAllChats(String session){
        SessionDTO sessionDTO = sessionRepository.findBySession(session)
                .orElseThrow(() -> new UserNotAuthorizedException());
        String login = sessionDTO.getLogin();
        List<ChatDTO> creatorChats = chatRepository.findByCreatorLogin(login)
                .orElse(Collections.emptyList());
        List<ChatDTO> memberChats = chatRepository.findByMemberLogin(login)
                .orElse(Collections.emptyList());
//        if (creatorChats.isEmpty() && memberChats.isEmpty()){
//            throw new NoChatsException();
//        }else{
//            creatorChats.addAll(memberChats);
//            return creatorChats;
//        }
        creatorChats.addAll(memberChats);
        return creatorChats;

    }
}
