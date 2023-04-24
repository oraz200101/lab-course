package com.example.onlinecourses.service;

import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getById(Long id);

    CategoryDto updateCategory(CategoryDto categoryDto);

    Page<CategoryDto> getAll(Pageable pageable);

    void deleteCategory(Long id);

    TagDto createTag(TagDto tagDto);

}
