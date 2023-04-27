package com.example.onlinecourses.service;

import com.example.onlinecourses.models.dto.CourseDto;
import org.springframework.web.multipart.MultipartFile;

public interface CourseTeacherService {
    CourseDto createCourse(CourseDto courseDto,MultipartFile file);
}
