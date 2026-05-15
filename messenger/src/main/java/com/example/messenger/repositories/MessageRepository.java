package com.example.messenger.repositories;

import com.example.messenger.database.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Messages,Long> {
}
