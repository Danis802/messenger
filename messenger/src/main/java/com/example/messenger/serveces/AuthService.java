//package com.example.messenger.serveces;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.sql.SQLException;
//
//public class AuthService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder encoder;
//
//    public AuthService(UserRepository userRepository, PasswordEncoder encoder) {
//        this.userRepository = userRepository;
//        this.encoder = encoder;
//    }
//
//    public void register(UserRequest user) {
//        try{
//            String encodedPassword = encoder.encode(user.getPassword());
//            userRepository.addUser(user, encodedPassword);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//    }
//}
