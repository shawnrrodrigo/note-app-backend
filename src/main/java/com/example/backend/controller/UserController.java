package com.example.backend.controller;

import com.example.backend.repository.UserRepository;
import com.example.backend.response.UserResponseDTO;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getCurrentUser(){
        UserResponseDTO userResponseDTO = userService.getCurrentUser();
        return ResponseEntity.ok(userResponseDTO);
    }
}
