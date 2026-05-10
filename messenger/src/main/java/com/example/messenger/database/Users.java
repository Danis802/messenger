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
    @Column(name="password", nullable = false, columnDefinition = "TEXT")
    private String password;

    public Users(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
