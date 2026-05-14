package com.example.messenger.repositories;

import com.example.messenger.database.Sessions;
import com.example.messenger.dto.SessionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Sessions,Long> {
    Optional<SessionDTO> findBySession(String session);
}
