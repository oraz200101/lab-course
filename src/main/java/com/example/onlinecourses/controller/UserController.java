package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;


    @PutMapping("/update")
    ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRegisterDto registerDto) {
        return ResponseEntity.ok(service.updateProfile(registerDto));
    }

}
