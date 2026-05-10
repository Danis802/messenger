package com.example.messenger.mapper;

import com.example.messenger.database.Sessions;
import com.example.messenger.dto.SessionDTO;

public class SessionMapper {
    public static SessionDTO mapSesToDTO (Sessions sessions){
        return new SessionDTO(sessions.getLogin(), sessions.getSession());
    }

    public static Sessions mapSesToJPA(SessionDTO sessionDTO){
        return new Sessions(sessionDTO.getLogin(), sessionDTO.getSession());
    }
}
