package com.example.backend.exception.custom;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final int code;
    public ResourceNotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }





}
