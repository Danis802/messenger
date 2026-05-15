package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.database.Messages;
import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.SessionDTO;
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
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new RuntimeException("Wrong chat id"));
        SessionDTO session = sessionRepository.findBySession(mesDTO.getSession())
                .orElseThrow(()-> new RuntimeException("User is not logged in account"));
        String login = session.getLogin();
        if (login.equals(chat.getCreatorLogin()) || login.equals(chat.getMemberLogin())){
            mesDTO.setSender(login);
            Messages savedMes = messageRepository.save(MessageMapper.mapToJPA(mesDTO));
            savedMes.setContent(null);
            return MessageMapper.mapToDTO(savedMes);
        }else{
            throw new RuntimeException("Sender is not member of chat");
        }
    }
}
