package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CourseRequestParamDto {

    private String search;
    private Integer pageNumber;

    private Integer pageSize;

    private String language;

    private BigDecimal rating;

    private String hours;

    private String sort;
}
