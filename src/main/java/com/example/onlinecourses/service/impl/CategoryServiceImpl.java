package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.exception.domain.NotFoundException;
import com.example.onlinecourses.mapper.CategoryMapper;
import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.dto.TagDto;
import com.example.onlinecourses.models.entities.Category;
import com.example.onlinecourses.models.entities.Tag;
import com.example.onlinecourses.repository.CategoryRepository;
import com.example.onlinecourses.repository.TagRepository;
import com.example.onlinecourses.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    private final TagRepository tagRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapper.mapToCategoryEntity(categoryDto);
        category = repository.save(category);
        return mapper.mapToCategoryDto(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("category with id not found"));
        return mapper.mapToCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = repository.findById(categoryDto.getId()).orElseThrow(() -> new NotFoundException("category with id not found"));
        category = repository.save(mapper.mapToCategoryEntity(categoryDto, category));
        return mapper.mapToCategoryDto(category);
    }

    @Override
    public Page<CategoryDto> getAll(Pageable pageable) {
        List<CategoryDto> categoryDtoList = mapper.mapToCategoryDtos(repository.findAll());
        return new PageImpl<>(categoryDtoList, pageable, categoryDtoList.size());
    }



    @Override
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TagDto createTag(TagDto tagDto) {
        Tag tag= mapper.mapToTagEntity(tagDto);
        tag=tagRepository.save(tag);
        return mapper.mapToTagDto(tag);
    }
}
