//package com.example.backend.controller;
//
//import com.example.backend.model.User;
//import com.example.backend.request.UserLoginRequestDTO;
//import com.example.backend.request.UserRegisterRequestDTO;
//import com.example.backend.response.UserLoginResponseDTO;
//import com.example.backend.security.service.AuthenticationService;
//import com.example.backend.security.service.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173/")
//@RequestMapping("/auth")
//public class AuthController {
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @PostMapping("/login")
//    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO loginRequestDTO){
//        User authenticatedUser = authenticationService.login(loginRequestDTO);
//        String jwtToken = jwtService.generateToken(authenticatedUser);
//        UserLoginResponseDTO loginResponseDTO = new UserLoginResponseDTO();
//        loginResponseDTO.setToken(jwtToken);
//        loginResponseDTO.setExpiresIn(jwtService.getExpirationTime());
//
//        return ResponseEntity.ok(loginResponseDTO);
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<User> register(@RequestBody UserRegisterRequestDTO registerRequestDTO){
//        User registeredUser = authenticationService.signup(registerRequestDTO);
//        return ResponseEntity.ok(registeredUser);
//    }
//}
