package com.example.messenger.database;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @SequenceGenerator(
            name="friends_sequence",
            sequenceName = "friends_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "friends_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "login", columnDefinition = "TEXT")
    private String login;
    @Column(name = "friendLogin", columnDefinition = "TEXT")
    private String friendLogin;

    public Friends(String login, String friendLogin) {
        this.login = login;
        this.friendLogin = friendLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFriendLogin() { return friendLogin; }

    public void setFriendLogin(String friendLogin) {
        this.friendLogin = friendLogin;
    }
}
