package com.example.onlinecourses.controller;


import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.service.CourseTeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher/course")
@RequiredArgsConstructor
public class CourseTeacherController {
    private final CourseTeacherService courseTeacherService;

    @PostMapping("/create")
    ResponseEntity<CourseDto> createCourse(@RequestBody @Valid CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseTeacherService.createCourse(courseDto));
    }


}
