package com.example.messenger.database;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="Message")
public class Message {
    @Id
    @SequenceGenerator(
            name="mes_sequense",
            sequenceName = "mes_sequense",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "mes_sequense"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    public Message(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
