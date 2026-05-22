package com.example.messenger.repositories;

import com.example.messenger.database.Messages;
import com.example.messenger.dto.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Messages,Long> {
    Optional<List<MessageDTO>> findByChatId(Long chatId);
}
