package com.example.onlinecourses.models.dto;

import com.example.onlinecourses.models.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType="Bearer";
    private Set<Role> roles;
    public AuthResponseDto(String accessToken,Set<Role> roles) {
        this.accessToken = accessToken;
        this.roles=roles;
    }
}
