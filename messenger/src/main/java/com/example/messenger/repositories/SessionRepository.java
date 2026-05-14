package com.example.messenger.repositories;

import com.example.messenger.database.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Sessions,Long> {
    Boolean findBySession(String session);
}
