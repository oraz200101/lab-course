package com.example.onlinecourses.service;

import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.CourseResponseDto;
import com.example.onlinecourses.models.enums.CourseHoursEnum;
import com.example.onlinecourses.models.enums.SortEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface CourseTeacherService {
    CourseDto createCourse(CourseDto courseDto,MultipartFile file);

    Page<CourseResponseDto> getCourseAll(Pageable pageable, BigDecimal rating, CourseHoursEnum hours, SortEnum sort);

    CourseResponseDto getById(Long id);

    CourseDto getBySubscription(Long id);

    CourseDto buyCourse(Long id);

    Object getBySubscriptionOrNot(Long id);
}
