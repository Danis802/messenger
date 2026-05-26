package com.example.messenger.repositories;

import com.example.messenger.database.Friends;
import com.example.messenger.dto.FriendsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friends,Long> {
    Optional<List<FriendsDTO>> findByLogin(String login);
    Optional<List<FriendsDTO>> findByFriendLogin(String friendLogin);
}
