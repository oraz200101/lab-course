package com.example.onlinecourses.service.feature;

import com.example.onlinecourses.exception.domain.CustomValidationException;
import com.example.onlinecourses.models.dto.CourseRequestParamDto;
import com.example.onlinecourses.models.dto.CourseResponseDto;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.QCourse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.QTuple;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.example.onlinecourses.utils.QueryDslUtil.optEnumEqPredicate;
import static com.example.onlinecourses.utils.QueryDslUtil.optGoeNumberPredicate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseQueryHelper {
    public static final QCourse COURSE = QCourse.course;
    private final EntityManager entityManager;

    public List<CourseResponseDto> formCourseResulList(List<Tuple> courseList, Function<Tuple, CourseResponseDto> courseMapper) {
        List<CourseResponseDto> resultList = new ArrayList<>();
        for (Tuple tuple : courseList) {
            CourseResponseDto courseResponseDto = courseMapper.apply(tuple);
            resultList.add(courseResponseDto);
        }
        return resultList;
    }

    public BooleanBuilder getCourseFilter(CourseRequestParamDto requestParamDto) {
        BooleanBuilder courseFilter = new BooleanBuilder();
        optEnumEqPredicate(COURSE.language, requestParamDto.getLanguage())
                .ifPresent(courseFilter::and);
        optGoeNumberPredicate(COURSE.rating, requestParamDto.getRating())
                .ifPresent(courseFilter::and);


        String searchText = requestParamDto.getSearch();
        if (StringUtils.hasText(searchText)) {
            courseFilter.and(COURSE.title.containsIgnoreCase(searchText));
        }

        if (requestParamDto.getHours() != null) {
            switch (requestParamDto.getHours()) {
                case "NULL_TO_ONE" -> courseFilter.and(COURSE.totalHours.loe(1));
                case "ONE_TO_THREE" -> courseFilter.and(COURSE.totalHours.gt(1).and(COURSE.totalHours.loe(3)));
                case "THREE_TO_SIX" -> courseFilter.and(COURSE.totalHours.gt(3).and(COURSE.totalHours.loe(6)));
                case "SIX_TO_SEVENTEEN" -> courseFilter.and(COURSE.totalHours.gt(6).and(COURSE.totalHours.loe(17)));
                case "MORE_SEVENTEEN" -> courseFilter.and(COURSE.totalHours.gt(17));
                default -> throw new CustomValidationException("invalid hour");
            }
        }
        return courseFilter;
    }

    public JPAQuery<Tuple> courseQuery(QTuple tuple) {
        return new JPAQuery<>(entityManager)
                .distinct()
                .select(tuple)
                .from(COURSE);
    }

    public QSort getCourseSort(CourseRequestParamDto requestParamDto) {
        QSort courseSort = new QSort();
        if (requestParamDto.getSort() != null) {
            switch (requestParamDto.getSort()) {
                case "MOST_POPULAR" -> courseSort.and(COURSE.buyCount.desc());
                case "HIGH_RATING" -> courseSort.and(COURSE.rating.desc());
                case "NEW" -> courseSort.and(COURSE.dateTimeCreate.desc());
            }
        }
        return courseSort;
    }


    public List<Tuple> fetchTuple(Pageable pageable, JPAQuery<Tuple> query) {
        return fetchTuple(pageable, query, COURSE.id.asc());
    }


    public List<Tuple> fetchTuple(Pageable pageable, JPAQuery<Tuple> query, OrderSpecifier<?>... order) {
        return query.limit(pageable.getPageSize())
                .offset((long) pageable.getPageNumber() * (long) pageable.getPageSize())
                .orderBy(order)
                .fetch();
    }
}
