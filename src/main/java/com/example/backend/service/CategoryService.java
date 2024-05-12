package com.example.backend.service;

import com.example.backend.request.CategoryRequestDTO;
import com.example.backend.response.CategoryResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    List<CategoryResponseDTO> getAllCategory();

    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    void deleteCategory(Long id);
}
