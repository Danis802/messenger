package com.example.messenger.dto;

public class FriendsDTO {
    private String login;
    private String friendLogin;

    public FriendsDTO(String login, String friendLogin) {
        this.login = login;
        this.friendLogin = friendLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFriendLogin() {
        return friendLogin;
    }

    public void setFriendLogin(String friendLogin) {
        this.friendLogin = friendLogin;
    }
}
