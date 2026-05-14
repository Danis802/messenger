package com.example.messenger.serveces;

import com.example.messenger.database.Sessions;
import com.example.messenger.database.Users;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.mapper.UserMapper;
import com.example.messenger.repositories.SessionRepository;
import com.example.messenger.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public UserDTO createUser(UserDTO userDTO){
        String sessionKey = UUID.randomUUID().toString();
        sessionRepository.save(new Sessions(userDTO.getLogin(), sessionKey));
        Users user = UserMapper.mapUserToJPA(userDTO);
        Users savedUser = userRepository.save(user);
        return UserMapper.mapUserToDTO(savedUser);
    }

    public UserDTO auth(UserDTO userDTO) {

        UserDTO user = userRepository.findByLogin(userDTO.getLogin())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getPassword().equals(userDTO.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return user;
    }
}
