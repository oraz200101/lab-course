package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class CourseResponseDto {
    private Long id;

    private String title;

    private String description;

    private Long price;

    private String author;

    private BigDecimal rating;

//    private String image;

    private Long totalHours;

    private String language;

    private Long lecturesQuantity;
}
