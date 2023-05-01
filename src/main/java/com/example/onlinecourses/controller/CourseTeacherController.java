package com.example.onlinecourses.controller;


import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.service.CourseTeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/teacher/course")
@RequiredArgsConstructor
public class CourseTeacherController {
    private final CourseTeacherService courseTeacherService;

    @PostMapping("/create")
    ResponseEntity<CourseDto> createCourse(@RequestPart("model") @Valid CourseDto courseDto, @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseTeacherService.createCourse(courseDto, image));
    }

    @PutMapping("/update")
    ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseTeacherService.updateCourse(courseDto));
    }

    @DeleteMapping("/{courseId}")
    ResponseEntity<?> deleteById(@PathVariable Long courseId) {
        courseTeacherService.deleteById(courseId);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{courseId}/subscription")
    ResponseEntity<?> buyCourseSubscription(@PathVariable Long courseId){
        courseTeacherService.buyCourseSubscription(courseId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    ResponseEntity<Page<CourseDto>> teacherCourses(
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "10", name = "count") Integer count) {
        return ResponseEntity.ok(courseTeacherService.teacherCourses(PageRequest.of(page, count)));
    }

    @GetMapping("/{courseId}")
    ResponseEntity<CourseDto> teacherCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseTeacherService.teacherCourse(courseId));
    }
}
