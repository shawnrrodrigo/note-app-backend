package com.example.backend.response;

import lombok.Data;

@Data
public class NoteResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String category;
}
