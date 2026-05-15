package com.example.messenger.repositories;

import com.example.messenger.database.Chat;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.SessionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat,Long>{
    Optional<ChatDTO> findByCreatorLogin(String login);
    Optional<ChatDTO> findByMemberLogin(String login);
    Optional<Chat> findById(Long id);
}
