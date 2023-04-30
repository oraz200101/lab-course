package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class SectionDto {
    private Long courseId;
    private String title;

    private List<ModuleDto> moduleDtos;

}
