package com.example.messenger.mapper;

import com.example.messenger.database.Friends;
import com.example.messenger.database.Messages;
import com.example.messenger.dto.FriendsDTO;
import com.example.messenger.dto.MessageDTO;

public class FriendsMapper {
    public static FriendsDTO mapToDTO (Friends friends){
        return new FriendsDTO(friends.getLogin(), friends.getFriendLogin());
    }

    public static Friends mapToJPA(FriendsDTO friends){
        return new Friends(friends.getLogin(), friends.getFriendLogin());
    }
}
