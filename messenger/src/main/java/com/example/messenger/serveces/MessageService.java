package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.database.Message;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.MessageDTO;
import com.example.messenger.mapper.ChatMapper;
import com.example.messenger.mapper.MessageMapper;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    private MessageRepository messageRepository;

    public MessageDTO createMessage(MessageDTO mesDTO){
        Message message = MessageMapper.mapToJPA(mesDTO);
        Message savedMes = messageRepository.save(message);
        return MessageMapper.mapToDTO(savedMes);
    }
}
