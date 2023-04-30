package com.example.onlinecourses.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AuthResponseDto tokenDto;
}
