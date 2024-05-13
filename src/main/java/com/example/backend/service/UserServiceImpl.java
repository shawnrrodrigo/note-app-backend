package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.response.UserResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserResponseDTO getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        User user = userRepository.findByUsername(username).orElseThrow();
//        UserResponseDTO userResponseDTO = new UserResponseDTO();
//        BeanUtils.copyProperties(user, userResponseDTO);
//        return userResponseDTO;
//    }
}
