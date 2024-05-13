//package com.example.backend.security.service;
//
//import com.example.backend.model.User;
//import com.example.backend.repository.UserRepository;
//import com.example.backend.request.UserLoginRequestDTO;
//import com.example.backend.request.UserRegisterRequestDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public User signup(UserRegisterRequestDTO input) {
//        User user = new User();
//        user.setUsername(input.getUsername());
//        user.setName(input.getName());
//        user.setPassword(passwordEncoder.encode(input.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public User login(UserLoginRequestDTO input) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getUsername(),
//                        input.getPassword()
//                )
//        );
//
//        return userRepository.findByUsername(input.getUsername())
//                .orElseThrow();
//    }
//
//}
