package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.dto.TagDto;
import com.example.onlinecourses.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @PutMapping("/update")
    ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tag/create")
    ResponseEntity<TagDto> createTag(@RequestBody @Valid TagDto tagDto) {
        return ResponseEntity.ok(categoryService.createTag(tagDto));
    }
}
