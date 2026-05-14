package com.example.messenger.serveces;

import com.example.messenger.database.Chat;
import com.example.messenger.database.Sessions;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.mapper.ChatMapper;
import com.example.messenger.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SessionService {
    final SessionRepository sessionRepository;
    private static final SecureRandom secureRandom = new SecureRandom();

    public SessionService(@Autowired SessionRepository sessionRepository){this.sessionRepository = sessionRepository;}


    public String addSession(String login){
        byte[] key = new byte[32];
        secureRandom.nextBytes(key);
        String code = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(key);
        Sessions session = new Sessions(login, code);
        sessionRepository.save(session);

        return code;
    }
}
