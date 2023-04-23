package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.mapper.CategoryMapper;
import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.entities.Category;
import com.example.onlinecourses.repository.CategoryRepository;
import com.example.onlinecourses.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapper.mapToCategoryEntity(categoryDto);
        category = repository.save(category);
        return mapper.mapToCategoryDto(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("category not found with id"));
        return mapper.mapToCategoryDto(category);
    }
}
