package com.example.messenger.serveces;

import com.example.messenger.database.Users;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.mapper.UserMapper;
import com.example.messenger.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){
        Users user = UserMapper.mapUserToJPA(userDTO);
        Users savedUser = userRepository.save(user);
        return UserMapper.mapUserToDTO(savedUser);
    }
}
