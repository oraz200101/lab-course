package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.exception.domain.NotFoundException;
import com.example.onlinecourses.mapper.CommentMapper;
import com.example.onlinecourses.models.dto.CommentDto;
import com.example.onlinecourses.models.entities.Comment;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.repository.CommentRepository;
import com.example.onlinecourses.repository.CourseRepository;
import com.example.onlinecourses.service.AuthenticationFacade;
import com.example.onlinecourses.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CourseRepository courseRepository;

    private final AuthenticationFacade authenticationFacade;
    private final CommentMapper mapper;



    @Override
    @Transactional
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = mapper.mapToEntity(commentDto);
        comment.setCreatedBy(authenticationFacade.getCurrentPrincipal());
        repository.save(comment);
        Course course = courseRepository.findById(commentDto.getCourseId()).orElseThrow(() -> new NotFoundException("course not found"));
        List<Comment> comments = course.getComments();
        BigDecimal average = getAverage(comments.stream().map(Comment::getRating).collect(Collectors.toList()));
        course.setRating(average);
        courseRepository.save(course);
        return mapper.mapToDto(comment);
    }

    private BigDecimal getAverage(List<BigDecimal> list) {
        BigDecimal sum = list.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int size = list.size();
        if (size == 0) {
            return BigDecimal.ZERO;
        } else {
            return sum.divide(BigDecimal.valueOf(size), 2, RoundingMode.HALF_UP);
        }
    }
}