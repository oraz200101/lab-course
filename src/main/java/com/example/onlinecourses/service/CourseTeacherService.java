package com.example.onlinecourses.service;

import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.CourseRequestParamDto;
import com.example.onlinecourses.models.dto.CourseResponseDto;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.enums.CourseHoursEnum;
import com.example.onlinecourses.models.enums.SortEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface CourseTeacherService {
    CourseDto createCourse(CourseDto courseDto, MultipartFile file);

    Page<CourseResponseDto> getCourseAll(Pageable pageable, BigDecimal rating, CourseHoursEnum hours, SortEnum sort);
    Page<CourseResponseDto> getAllCourseV2(CourseRequestParamDto requestParamDto);
    CourseResponseDto getById(Long id);

    void buyCourseSubscription(Long id);

    CourseDto getBySubscription(Long id);

    CourseDto buyCourse(Long id);

    Object getBySubscriptionOrNot(Long id);

    Page<CourseDto> teacherCourses(Pageable pageable);

    CourseDto teacherCourse(Long courseId);

    CourseDto updateCourse(CourseDto courseDto);

    List<CourseResponseDto> getBySubscription();

    Page<CourseResponseDto> getBySearch(String title,Pageable pageable);

    void deleteById(Long courseId);
}
