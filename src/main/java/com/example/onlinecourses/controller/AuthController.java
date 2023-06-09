package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.AuthResponseDto;
import com.example.onlinecourses.models.dto.UserLoginDto;
import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto){
        userService.registerUser(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid UserLoginDto userLoginDto){
        return ResponseEntity.ok(userService.loginUser(userLoginDto));
    }

}
