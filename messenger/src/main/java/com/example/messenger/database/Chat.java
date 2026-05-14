package com.example.messenger.database;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="Chat")
public class Chat {
    @Id
    @SequenceGenerator(
            name="chat_sequense",
            sequenceName = "chat_sequense",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "chat_sequense"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "creator", nullable = false, columnDefinition = "TEXT")
    private String creatorLogin;
    @Column(name = "member", nullable = false, columnDefinition = "TEXT")
    private String memberLogin;

    public Chat(Long id, String name, String creatorLogin, String memberLogin) {
        this.id = id;
        this.name = name;
        this.creatorLogin = creatorLogin;
        this.memberLogin = memberLogin;
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

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
