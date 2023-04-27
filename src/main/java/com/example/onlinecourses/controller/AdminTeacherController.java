package com.example.onlinecourses.controller;

import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/teacher")
@RequiredArgsConstructor
public class AdminTeacherController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        userService.createTeacher(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserResponseDto>> getTeachers(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                                             @RequestParam(defaultValue = "10", name = "count") Integer count) {
        return ResponseEntity.ok(userService.getTeachers(PageRequest.of(page, count)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }


}
