package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;

}
