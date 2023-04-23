package com.example.onlinecourses.service;

import com.example.onlinecourses.models.dto.AuthResponseDto;
import com.example.onlinecourses.models.dto.UserLoginDto;
import com.example.onlinecourses.models.dto.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    AuthResponseDto loginUser(UserLoginDto userLoginDto);

    void registerUser(UserRegisterDto userRegisterDto);
}
