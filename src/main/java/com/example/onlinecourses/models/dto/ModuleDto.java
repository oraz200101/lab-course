package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModuleDto {
    private String title;

    private String videoLink;

    private Long duration;
}
