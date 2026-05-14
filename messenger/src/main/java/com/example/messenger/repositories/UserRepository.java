package com.example.messenger.repositories;

import com.example.messenger.database.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String> {
    Optional<Users> findByLogin(String login);
}
