package com.example.messenger.dto;

public class SessionDTO {
    private String login;
    private String Session;

    public SessionDTO(String login, String session) {
        this.login = login;
        Session = session;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }
}
