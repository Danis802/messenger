package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.database.Message;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.SessionDTO;
import com.example.messenger.mapper.ChatMapper;
import com.example.messenger.mapper.MessageMapper;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.MessageRepository;
import com.example.messenger.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    private MessageRepository messageRepository;
    private SessionRepository sessionRepository;
    private ChatRepository chatRepository;

    public MessageDTO createMessage(MessageDTO mesDTO, Long chatId){
        SessionDTO senderSession = sessionRepository.findBySession(mesDTO.getSession())
                .orElseThrow(() ->
                        new RuntimeException("User is not logged in"));
        String login = senderSession.getLogin();
        if (!chatRepository.findByCreatorLogin(login) && !chatRepository.findByMemberLogin(login)){
            throw new RuntimeException("User is not member of this chat");
        }
        mesDTO.setSender(login);
        mesDTO.setId(chatId);
        Message message = MessageMapper.mapToJPA(mesDTO);
        Message savedMes = messageRepository.save(message);
        return MessageMapper.mapToDTO(savedMes);
    }
}
