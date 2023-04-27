package com.example.onlinecourses.service;

import com.example.onlinecourses.models.dto.AuthResponseDto;
import com.example.onlinecourses.models.dto.UserLoginDto;
import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    AuthResponseDto loginUser(UserLoginDto userLoginDto);

    void registerUser(UserRegisterDto userRegisterDto);

    void createTeacher(UserRegisterDto userRegisterDto);

    UserResponseDto updateProfile(UserRegisterDto userRegisterDto);

    Page<UserResponseDto> getTeachers(Pageable pageable);

    UserResponseDto getById(Long id);
}
