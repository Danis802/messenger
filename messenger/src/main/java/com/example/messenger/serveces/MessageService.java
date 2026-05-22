package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.database.Messages;
import com.example.messenger.database.Users;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.SessionDTO;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.exceptions.NoChatsException;
import com.example.messenger.exceptions.UserNotAuthorizedException;
import com.example.messenger.mapper.MessageMapper;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.MessageRepository;
import com.example.messenger.repositories.SessionRepository;
import com.example.messenger.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MessageService {
    private MessageRepository messageRepository;
    private SessionRepository sessionRepository;
    private ChatRepository chatRepository;
    private UserRepository userRepository;

    public MessageDTO createMessage(MessageDTO mesDTO, Long chatId){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new RuntimeException("Wrong chat id"));
        SessionDTO session = sessionRepository.findBySession(mesDTO.getSession())
                .orElseThrow(()-> new RuntimeException("User is not logged in account"));
        String login = session.getLogin();
        if (login.equals(chat.getCreatorLogin()) || login.equals(chat.getMemberLogin())){
            mesDTO.setSender(login);
            mesDTO.setChatId(chatId);
            Messages savedMes = messageRepository.save(MessageMapper.mapToJPA(mesDTO));
            savedMes.setContent(null);
            return MessageMapper.mapToDTO(savedMes);
        }else{
            throw new RuntimeException("Sender is not member of chat");
        }
    }

    public List<MessageDTO> getAllMessages(Long chatid, String session){
        SessionDTO sessionDTO = sessionRepository.findBySession(session)
                .orElseThrow(() -> new UserNotAuthorizedException());
        String login = sessionDTO.getLogin();
        List<ChatDTO> creatorChats = chatRepository.findByCreatorLogin(login)
                .orElse(Collections.emptyList());
        List<ChatDTO> memberChats = chatRepository.findByMemberLogin(login)
                .orElse(Collections.emptyList());
        if (creatorChats.isEmpty() && memberChats.isEmpty()){
            throw new NoChatsException();
        }else{
            List<MessageDTO> messages = messageRepository.findByChatId(chatid)
                    .orElse(Collections.emptyList());
            Chat chat = chatRepository.findById(chatid)
                    .orElseThrow(() ->new RuntimeException("no chat with id"));
            Users member = userRepository.findByLogin(chat.getMemberLogin())
                    .orElseThrow(()-> new RuntimeException("no user"));
            Users creator = userRepository.findByLogin(chat.getCreatorLogin())
                    .orElseThrow(()-> new RuntimeException("no user"));
            for (MessageDTO mes: messages){
                if (mes.getSender().equals(member.getLogin())){
                    mes.setSender(member.getName());
                }
                if (mes.getSender().equals(creator.getLogin())){
                    mes.setSender(creator.getName());
                }
            }
            return messages;
        }
    }
}
