package com.example.messenger.serveces;

import com.example.messenger.database.Users;
import com.example.messenger.dto.FriendsDTO;
import com.example.messenger.dto.SessionDTO;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.exceptions.LoginAlreadyExistsException;
import com.example.messenger.exceptions.UserNotAuthorizedException;
import com.example.messenger.exceptions.UserNotFoundException;
import com.example.messenger.mapper.UserMapper;
import com.example.messenger.repositories.FriendsRepository;
import com.example.messenger.repositories.SessionRepository;
import com.example.messenger.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private FriendsRepository friendsRepository;

    public UserDTO createUser(UserDTO userDTO){
        if (userRepository.findByLogin(userDTO.getLogin()).isEmpty()){
            Users user = UserMapper.mapUserToJPA(userDTO);
            Users savedUser = userRepository.save(user);
            return UserMapper.mapUserToDTO(savedUser);
        }
        else{
            throw new LoginAlreadyExistsException();
        }
    }

    public UserDTO auth(UserDTO userDTO) {

        Users user = userRepository.findByLogin(userDTO.getLogin())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getPassword().equals(userDTO.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        SessionDTO session = sessionRepository.findByLogin(userDTO.getLogin())
                .orElseThrow(() ->
                        new RuntimeException("User has no session"));
        UserDTO loggedUser = UserMapper.mapUserToDTO(user);
        loggedUser.setSession(session.getSession());
        return loggedUser;
    }

    public List<UserDTO> getAllFriends(String session){
        SessionDTO sessionDTO = sessionRepository.findBySession(session)
                .orElseThrow(() -> new UserNotAuthorizedException());
        List<FriendsDTO> friends = friendsRepository.findByFriendLogin(sessionDTO.getLogin())
                .orElse(Collections.emptyList());
        List<FriendsDTO> users = friendsRepository.findByLogin(sessionDTO.getLogin())
                .orElse(Collections.emptyList());
        friends.addAll(users);
        List<UserDTO> friendsList = Collections.emptyList();
        for (FriendsDTO fr : friends){
            String login;
            if (sessionDTO.getLogin().equals(fr.getFriendLogin())){
                login = fr.getFriendLogin();
            }else{
                login = fr.getLogin();
            }
            Users user = userRepository.findByLogin(login)
                            .orElseThrow(()-> new UserNotFoundException());
            friendsList.add(new UserDTO(login, user.getName(), null, null));
        }
        return friendsList;
    }

    public UserDTO getLoginBySession(String session){
        SessionDTO sessionDTO = sessionRepository.findBySession(session)
                .orElseThrow(() -> new UserNotAuthorizedException());
        Users user = userRepository.findByLogin(sessionDTO.getLogin())
                .orElseThrow(()-> new UserNotFoundException());
        UserDTO userDTO = UserMapper.mapUserToDTO(user);
        userDTO.setSession(null);
        userDTO.setPassword(null);
        return userDTO;
    }
}
