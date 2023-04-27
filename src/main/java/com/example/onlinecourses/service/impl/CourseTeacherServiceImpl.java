package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.mapper.CourseMapper;
import com.example.onlinecourses.mapper.FileMapper;
import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.FileDto;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.FileStorage;
import com.example.onlinecourses.repository.CourseRepository;
import com.example.onlinecourses.service.AuthenticationFacade;
import com.example.onlinecourses.service.CourseTeacherService;
import com.example.onlinecourses.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;




@Service
@RequiredArgsConstructor
public class CourseTeacherServiceImpl implements CourseTeacherService {
    private final CourseRepository repository;
    private final CourseMapper mapper;
    private final AuthenticationFacade authenticationFacade;

    private final FileStorageService fileStorageService;

    private final FileMapper fileMapper;

    @Override
    public CourseDto createCourse(CourseDto courseDto, MultipartFile file) {
        Course course = mapper.mapToCourseEntity(courseDto);
        course.setAuthor(authenticationFacade.getCurrentPrincipal());
        FileStorage image = fileStorageService.upload(file);
        course.setImage(image);
        course.setSubscription(Boolean.FALSE);
        course = repository.save(course);
        FileDto fileDto = fileDtoBuilder(course.getImage());
        CourseDto newCourseDto = mapper.mapToCourseDto(course);
        newCourseDto.setFileDto(fileDto);
        return newCourseDto;
    }

    private FileDto fileDtoBuilder(FileStorage fileStorage) {
        FileDto fileDto = fileMapper.mapToFileDto(fileStorage);
        fileDto.setUrl(fileLinkBuilder(fileDto.getId()));
        return fileDto;
    }

    private String fileLinkBuilder(String fileId) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/files/")
                .path(fileId)
                .toUriString();
    }


}
