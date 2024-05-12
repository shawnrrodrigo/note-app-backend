package com.example.backend.service;

import com.example.backend.exception.custom.ResourceNotFoundException;
import com.example.backend.model.Category;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.request.CategoryRequestDTO;
import com.example.backend.response.CategoryResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponseDTO> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map((category)->{
                    CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
                    BeanUtils.copyProperties(category, categoryResponseDTO);
                    return categoryResponseDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequestDTO, category);
        categoryRepository.save(category);
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        BeanUtils.copyProperties(category, categoryResponseDTO);
        return categoryResponseDTO;
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        Category category = existingCategory.orElseThrow(()-> new ResourceNotFoundException("Invalid category", HttpStatus.NOT_FOUND.value()));
        categoryRepository.delete(category);
    }
}
