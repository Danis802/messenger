package com.example.messenger.repositories;

import com.example.messenger.database.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long>{

}
