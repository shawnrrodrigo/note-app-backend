package com.example.backend.request;

import com.example.backend.model.Category;
import lombok.Data;

@Data
public class NoteRequestDTO {
    private Long id;
    private String title;
    private String content;
    private String category;
}
