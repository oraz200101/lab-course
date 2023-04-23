package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class SectionDto {
    private String title;

    private List<ModuleDto> moduleDtos;

}
