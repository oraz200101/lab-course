package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CourseDto {

    private Long id;

    private String description;

    private Long price;

    private BigDecimal rating;

    private LocalDateTime dateTimeCreate;

    private LocalDateTime dateTimeUpdate;

    private List<ObjectiveDto> objectiveDtos;

    private Long categoryId;

    private List<SectionDto> sectionDtos;


}
