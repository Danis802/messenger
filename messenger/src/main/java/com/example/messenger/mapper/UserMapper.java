package com.example.messenger.mapper;

import com.example.messenger.database.Users;
import com.example.messenger.dto.UserDTO;

public class UserMapper {
    public static UserDTO mapUserToDTO (Users user){
        return new UserDTO(user.getLogin(), user.getName(), null, null);
    }

    public static Users mapUserToJPA(UserDTO user){
        return new Users(user.getLogin(), user.getName());
    }
}
