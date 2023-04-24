package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.mapper.CourseMapper;
import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.ModuleDto;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.Section;
import com.example.onlinecourses.repository.CourseRepository;
import com.example.onlinecourses.service.AuthenticationFacade;
import com.example.onlinecourses.service.CourseTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class CourseTeacherServiceImpl implements CourseTeacherService {
    private final CourseRepository repository;
    private final CourseMapper mapper;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = mapper.mapToCourseEntity(courseDto);
        course.setAuthor(authenticationFacade.getCurrentPrincipal());
        course = repository.save(course);
        return mapper.mapToCourseDto(course);
    }


}
