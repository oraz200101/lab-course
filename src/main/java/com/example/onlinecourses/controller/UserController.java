package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.service.AuthenticationFacade;
import com.example.onlinecourses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final AuthenticationFacade authenticationFacade;

    @PutMapping("/update")
    ResponseEntity<String> updateUser(@RequestBody UserRegisterDto registerDto) {
        return ResponseEntity.ok(service.updateProfile(registerDto));
    }

    @GetMapping("/myProfile")
    ResponseEntity<UserResponseDto> getProfile() {
        return ResponseEntity.ok(service.getById(authenticationFacade.getCurrentPrincipal().getId()));
    }
}
