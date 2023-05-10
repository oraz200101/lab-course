package com.example.onlinecourses.service.feature;

import com.example.onlinecourses.mapper.CourseMapper;
import com.example.onlinecourses.models.dto.CourseRequestParamDto;
import com.example.onlinecourses.models.dto.CourseResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QTuple;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static com.example.onlinecourses.service.feature.CourseQueryHelper.COURSE;

@Service
@RequiredArgsConstructor
public class CourseViewServiceImpl implements CourseViewService {

    private final CourseQueryHelper helper;
    private final CourseMapper mapper;

    @Override
    public Page<CourseResponseDto> fetchListCourse(CourseRequestParamDto requestParamDto) {
        Pageable pageable = PageRequest.of(requestParamDto.getPageNumber(), requestParamDto.getPageSize());
        BooleanBuilder courseFilter = helper.getCourseFilter(requestParamDto);

        QTuple tuple = Projections.tuple(COURSE);
        JPAQuery<Tuple> query = helper.courseQuery(tuple)
                .where(courseFilter);

        long courseCount = query.stream().count();
        List<Tuple> courseList = helper.fetchTuple(pageable, query);

        Function<Tuple, CourseResponseDto> courseMapper = defaultCourseMapper();
        List<CourseResponseDto> resultList = helper.formCourseResulList(courseList, courseMapper);

        return null;
    }

    private Function<Tuple, CourseResponseDto> defaultCourseMapper() {
        return it -> mapper.mapToCourseResponseDto(it.get(COURSE));
    }
}
