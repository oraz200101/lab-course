package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.CommentDto;
import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.service.AuthenticationFacade;
import com.example.onlinecourses.service.CommentService;
import com.example.onlinecourses.service.CourseTeacherService;
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
    private final CourseTeacherService courseTeacherService;

    private final CommentService commentService;

    @PutMapping("/update")
    ResponseEntity<String> updateUser(@RequestBody UserRegisterDto registerDto) {
        return ResponseEntity.ok(service.updateProfile(registerDto));
    }

    @GetMapping("/myProfile")
    ResponseEntity<UserResponseDto> getProfile() {
        return ResponseEntity.ok(service.getById(authenticationFacade.getCurrentPrincipal().getId()));
    }
    @PostMapping("/buy/{courseId}")
    ResponseEntity<CourseDto> buyCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseTeacherService.buyCourse(courseId));
    }
    @PostMapping("/comment")
    ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.createComment(commentDto));
    }

}
