package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagDto {
    private String name;

    private Long categoryId;
}
