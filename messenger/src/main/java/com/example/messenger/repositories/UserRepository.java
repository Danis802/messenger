package com.example.messenger.repositories;

import com.example.messenger.database.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
}
