package com.example.messenger.repositories;

import com.example.messenger.database.Chat;
import com.example.messenger.database.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friends,Long> {

}
