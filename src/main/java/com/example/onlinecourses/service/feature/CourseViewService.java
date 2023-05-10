package com.example.onlinecourses.service.feature;

import com.example.onlinecourses.models.dto.CourseRequestParamDto;
import com.example.onlinecourses.models.dto.CourseResponseDto;
import org.springframework.data.domain.Page;

public interface CourseViewService {

    Page<CourseResponseDto> fetchListCourse(CourseRequestParamDto requestParamDto);
}
