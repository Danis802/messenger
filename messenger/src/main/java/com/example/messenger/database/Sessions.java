package com.example.messenger.database;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="sessions")
public class Sessions {
    @Id
    @SequenceGenerator(
            name="session_sequense",
            sequenceName = "session_sequense",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "session_sequense"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "login", columnDefinition = "TEXT")
    private String login;
    @Column(name = "session", columnDefinition = "TEXT")
    private String session;

    public Sessions(String login, String session) {
        this.login = login;
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
