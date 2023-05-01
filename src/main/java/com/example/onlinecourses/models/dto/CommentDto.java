package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long courseId;

    private String text;

    private BigDecimal rating;

    private LocalDateTime dateTimeCreate;

    private UserResponseDto createdBy;

}
