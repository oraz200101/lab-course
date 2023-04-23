package com.example.onlinecourses.service;

import com.example.onlinecourses.models.dto.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getById(Long id);

}
