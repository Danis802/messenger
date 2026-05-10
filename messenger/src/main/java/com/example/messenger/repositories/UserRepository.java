package com.example.messenger.repositories;

import com.example.messenger.database.Users;
import com.example.messenger.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String> {
    Optional<UserDTO> findByLogin(String login);
}
