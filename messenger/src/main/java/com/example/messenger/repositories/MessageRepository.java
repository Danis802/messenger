package com.example.messenger.repositories;

import com.example.messenger.database.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
