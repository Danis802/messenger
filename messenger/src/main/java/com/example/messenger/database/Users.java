package com.example.messenger.database;

import jakarta.persistence.*;

@Entity
@Table(name="Users")
public class Users {
    @Id
    @Column(name = "login", updatable = false, columnDefinition = "TEXT")
    private String login;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    public Users(String login, String name) {
        this.login = login;
        this.name = name;
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
