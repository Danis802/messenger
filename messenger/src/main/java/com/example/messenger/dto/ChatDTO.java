package com.example.messenger.dto;

import java.util.List;

public class ChatDTO {
    private Long id;
    private String name;
    private String creatorLogin;
    private String memberLogin;
    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public void setCreatorLogin(String creatorLogin) {
        this.creatorLogin = creatorLogin;
    }

    public String getMemberLogin() {
        return memberLogin;
    }

    public void setMemberLogin(String memberLogin) {
        this.memberLogin = memberLogin;
    }

    public ChatDTO(Long id, String name, String creatorLogin, String memberLogin){
        this.id = id;
        this.name = name;
        this.creatorLogin = creatorLogin;
        this.memberLogin = memberLogin;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
