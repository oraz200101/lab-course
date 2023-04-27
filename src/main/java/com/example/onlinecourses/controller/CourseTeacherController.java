package com.example.onlinecourses.controller;


import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.service.CourseTeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/teacher/course")
@RequiredArgsConstructor
public class CourseTeacherController {
    private final CourseTeacherService courseTeacherService;

    @PostMapping("/create")
    ResponseEntity<CourseDto> createCourse(@RequestPart("model") @Valid CourseDto courseDto, @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseTeacherService.createCourse(courseDto, image));
    }




}
