package com.example.messenger.repositories;

import com.example.messenger.database.Chat;
import com.example.messenger.dto.SessionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat,Long>{
    Boolean findByCreatorLogin(String login);
    Boolean findByMemberLogin(String login);
}
