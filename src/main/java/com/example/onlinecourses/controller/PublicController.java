package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    private final CategoryService categoryService;

    @GetMapping("/category/{id}")
    ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/category/all")
    ResponseEntity<Page<CategoryDto>> getAll(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                             @RequestParam(defaultValue = "10", name = "count") Integer count) {
        return ResponseEntity.ok(categoryService.getAll(PageRequest.of(page, count)));
    }
}
