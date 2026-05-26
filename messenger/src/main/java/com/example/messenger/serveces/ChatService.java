package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.SessionDTO;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.exceptions.NoChatsException;
import com.example.messenger.exceptions.UserNotAuthorizedException;
import com.example.messenger.exceptions.UserNotFoundException;
import com.example.messenger.exceptions.WrongIdException;
import com.example.messenger.mapper.ChatMapper;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {
    private ChatRepository chatRepository;
    private SessionRepository sessionRepository;
    private static final SecureRandom secureRandom = new SecureRandom();

    public ChatDTO createChat(ChatDTO chatDTO){
        SessionDTO sessionDTO = sessionRepository.findBySession(chatDTO.getSession())
                .orElseThrow(()-> new RuntimeException("User is not logged in"));
        String creatorLogin = sessionDTO.getLogin();
        if (sessionRepository.findByLogin(chatDTO.getMemberLogin()).isPresent()){
            byte[] key = new byte[32];
            secureRandom.nextBytes(key);
            String code = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(key);
            chatDTO.setKey(code);
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
        creatorChats.addAll(memberChats);
        return creatorChats;

    }

    public String getKey(Long id, String session){
        Chat chat = chatRepository.findById(id)
                .orElseThrow(()-> new WrongIdException());
        SessionDTO sessionDTO = sessionRepository.findBySession(session)
                .orElseThrow(()-> new UserNotFoundException());
        if (!chat.getCreatorLogin().equals(sessionDTO.getLogin()) && !chat.getMemberLogin().equals(sessionDTO.getLogin())){
            throw new NoChatsException();
        }else{
            return chat.getKey();
        }
    }
}
