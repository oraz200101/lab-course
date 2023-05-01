package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.CommentDto;
import com.example.onlinecourses.models.entities.Comment;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface CommentMapper {
    @Mapping(source = "courseId", target = "course.id")
    Comment mapToEntity(CommentDto commentDto);

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "createdBy",target = "createdBy")
    CommentDto mapToDto(Comment comment);
}
