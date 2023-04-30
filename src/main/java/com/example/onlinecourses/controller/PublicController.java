package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.CourseResponseDto;
import com.example.onlinecourses.models.enums.CourseHoursEnum;
import com.example.onlinecourses.models.enums.SortEnum;
import com.example.onlinecourses.service.CategoryService;
import com.example.onlinecourses.service.CourseTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    private final CategoryService categoryService;
    private final CourseTeacherService courseService;

    @GetMapping("/category/{id}")
    ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/category/all")
    ResponseEntity<Page<CategoryDto>> getAll(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                             @RequestParam(defaultValue = "10", name = "count") Integer count) {
        return ResponseEntity.ok(categoryService.getAll(PageRequest.of(page, count)));
    }

    @GetMapping("/course/all")
    ResponseEntity<Page<CourseResponseDto>> getAllCourse(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                                         @RequestParam(defaultValue = "10", name = "count") Integer count,
                                                         @RequestParam(name = "rating", required = false) BigDecimal rating,
                                                         @RequestParam(name = "hours", required = false) String hours,
                                                         @RequestParam(name = "sort", required = false) String sort
    ) {
        SortEnum sort1;
        if (sort != null) {
            sort1 = SortEnum.valueOf(sort);
        } else {
            sort1 = null;
        }
        CourseHoursEnum hours1;
        if (hours != null) {
            hours1 = CourseHoursEnum.valueOf(hours);
        } else {
            hours1 = null;
        }
        return ResponseEntity.ok(courseService.getCourseAll(PageRequest.of(page, count), rating, hours1, sort1));
    }

    @GetMapping("/course/{id}")
    ResponseEntity<?> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getBySubscriptionOrNot(id));
    }

}
