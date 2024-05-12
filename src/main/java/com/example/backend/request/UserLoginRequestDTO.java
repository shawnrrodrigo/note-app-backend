package com.example.backend.request;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
