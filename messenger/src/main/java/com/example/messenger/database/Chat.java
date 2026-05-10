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

    public Chat(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private Set<ChatToUser> userChats = new HashSet<>();
}
