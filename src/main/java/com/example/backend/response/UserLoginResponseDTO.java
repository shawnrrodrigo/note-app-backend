package com.example.backend.response;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String token;
    private long expiresIn;
}
