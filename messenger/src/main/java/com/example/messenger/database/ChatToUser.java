//package com.example.messenger.database;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "UsersToChats")
//public class ChatToUser {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // проще, чем composite key
//
//    @ManyToOne
//    @JoinColumn(name = "user_login", nullable = false)
//    private Users user;
//
//    @ManyToOne
//    @JoinColumn(name = "chat_id", nullable = false)
//    private Chat chat;
//
//    public ChatToUser() {}
//
//    public ChatToUser(Users user, Chat chat) {
//        this.user = user;
//        this.chat = chat;
//    }
//}
