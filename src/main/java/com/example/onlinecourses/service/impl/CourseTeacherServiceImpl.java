package com.example.onlinecourses.service.impl;


import com.example.onlinecourses.exception.domain.CustomValidationException;
import com.example.onlinecourses.exception.domain.NotFoundException;
import com.example.onlinecourses.mapper.CourseMapper;
import com.example.onlinecourses.mapper.FileMapper;
import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.CourseResponseDto;
import com.example.onlinecourses.models.dto.FileDto;
import com.example.onlinecourses.models.entities.*;
import com.example.onlinecourses.models.entities.Module;
import com.example.onlinecourses.models.enums.CourseHoursEnum;
import com.example.onlinecourses.models.enums.SortEnum;
import com.example.onlinecourses.repository.CourseRepository;
import com.example.onlinecourses.repository.ObjectiveRepository;
import com.example.onlinecourses.repository.SectionRepository;
import com.example.onlinecourses.repository.UserCourseLinkRepository;
import com.example.onlinecourses.service.AuthenticationFacade;
import com.example.onlinecourses.service.CourseTeacherService;
import com.example.onlinecourses.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CourseTeacherServiceImpl implements CourseTeacherService {
    private final CourseRepository repository;
    private final UserCourseLinkRepository courseLinkRepository;
    private final CourseMapper mapper;
    private final AuthenticationFacade authenticationFacade;

    private final FileStorageService fileStorageService;

    private final FileMapper fileMapper;

    private final SectionRepository sectionRepository;

    private final ObjectiveRepository objectiveRepository;

    @Override
    @Transactional
    public CourseDto createCourse(CourseDto courseDto, MultipartFile file) {
        Course course = mapper.mapToCourseEntity(courseDto);
        course.setAuthor(authenticationFacade.getCurrentPrincipal());
        FileStorage image = fileStorageService.upload(file);
        course.setImage(image);
        course.setSubscription(Boolean.FALSE);
        course = repository.save(course);
        sectionRepository.saveAll(setSectionsToCourse(course, courseDto));
        objectiveRepository.saveAll(setObjectivesToCourse(course, courseDto));
        FileDto fileDto = fileDtoBuilder(course.getImage());
        CourseDto newCourseDto = mapper.mapToCourseDto(course);
        newCourseDto.setFileDto(fileDto);
        return newCourseDto;
    }

    @Override
    @Transactional
    public Page<CourseResponseDto> getCourseAll(Pageable pageable, BigDecimal rating, CourseHoursEnum hours, SortEnum sort) {
        String sort1;
        if (sort != null) {
            sort1 = String.valueOf(sort);
        } else {
            sort1 = null;
        }
        String hours1;
        if (hours != null) {
            hours1 = String.valueOf(hours);
        } else {
            hours1 = null;
        }
        List<Course> courses = repository.findByRating(rating, hours1, sort1);
        if (sort == null || sort.equals(SortEnum.NEW)) {
            courses.sort(Comparator.comparing(Course::getDateTimeCreate).reversed());
        }
        List<CourseResponseDto> courseResponseDtos = courses.stream().map(c -> {
            CourseResponseDto courseResponseDto = mapper.mapToCourseResponseDto(c);
            courseResponseDto.setFileDto(fileDtoBuilder(c.getImage()));
            return courseResponseDto;
        }).toList();
        return new PageImpl<>(courseResponseDtos, pageable, courseResponseDtos.size());
    }


    @Override
    @Transactional
    public CourseDto buyCourse(Long courseId) {
        Course course = repository.findById(courseId).orElseThrow(() -> new NotFoundException("course with " + courseId + "not found"));
        User user = authenticationFacade.getCurrentPrincipal();
        UserCourseLink userCourseLink = new UserCourseLink();
        userCourseLink.setCourse(course);
        userCourseLink.setUser(user);
        return mapper.mapToCourseDto(course);
    }

    @Override
    @Transactional
    public Object getBySubscriptionOrNot(Long id) {
        Long userId = authenticationFacade.getCurrentPrincipal().getId();
        if (courseLinkRepository.existsByCourse_IdAndUser_Id(id, userId)) {
            return getBySubscription(id);
        } else {
            return getById(id);
        }
    }

    @Override
    public CourseResponseDto getById(Long id) {
        Course course = repository.findById(id).orElseThrow(() -> new NotFoundException("course with " + id + " not found"));
        CourseResponseDto courseResponseDto = mapper.mapToCourseResponseDto(course);
        courseResponseDto.setFileDto(fileDtoBuilder(course.getImage()));
        return courseResponseDto;
    }

    @Override
    public CourseDto getBySubscription(Long id) {
        Course course = repository.findByIdAndCourseLinks_User_Id(id, authenticationFacade.getCurrentPrincipal().getId()).orElseThrow(() -> new CustomValidationException("You have not subscription"));
        CourseDto courseDto = mapper.mapToCourseDto(course);
        courseDto.setFileDto(fileDtoBuilder(course.getImage()));
        return courseDto;
    }


    private FileDto fileDtoBuilder(FileStorage fileStorage) {
        FileDto fileDto = fileMapper.mapToFileDto(fileStorage);
        fileDto.setUrl(fileLinkBuilder(fileDto.getId()));
        return fileDto;
    }

    private String fileLinkBuilder(String fileId) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(fileId)
                .toUriString();
    }

    private List<Section> setSectionsToCourse(Course course, CourseDto courseDto) {
        List<Section> sectionList = mapper.mapToSectionEntities(courseDto.getSectionDtos());
        Set<Section> sectionSet = new HashSet<>(sectionRepository.findByCourse(course));
        sectionSet.removeAll(new HashSet<>(sectionList));
        sectionRepository.deleteAll(sectionSet);
        sectionList.forEach(s -> {
            s.setCourse(course);
            setModulesToSection(s, s.getModules());
        });
        return sectionList;
    }

    private List<Objective> setObjectivesToCourse(Course course, CourseDto courseDto) {
        List<Objective> objectiveList = mapper.mapToObjectiveDtos(courseDto.getObjectiveDtos());
        Set<Objective> objectiveSet = new HashSet<>(objectiveRepository.findByCourse(course));
        objectiveSet.removeAll(new HashSet<>(objectiveList));
        objectiveRepository.deleteAll(objectiveSet);
        objectiveList.forEach(s -> s.setCourse(course));
        return objectiveList;
    }


    private void setModulesToSection(Section section, List<Module> modules) {
        modules.forEach(m -> m.setSection(section));
    }


}