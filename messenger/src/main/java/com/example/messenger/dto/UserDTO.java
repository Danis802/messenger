package com.example.messenger.dto;

public class UserDTO {
    private String login;
    private String name;
    private String password;
    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO(String login, String name, String password, String session) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.session = session;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
