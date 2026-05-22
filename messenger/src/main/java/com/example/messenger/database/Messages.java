package com.example.messenger.database;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="Messages")
public class Messages {
    @Id
    @SequenceGenerator(
            name="mes_sequence",
            sequenceName = "mes_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "mes_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "sender", nullable = false, columnDefinition = "TEXT")
    private String sender;
    @Column(name = "chatId", nullable = false)
    private Long chatId;

    public Messages(Long id, String content, String sender, Long chatId) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.chatId = chatId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

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
