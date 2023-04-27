package com.example.onlinecourses.controller;

import com.example.onlinecourses.mapper.FileMapper;
import com.example.onlinecourses.models.entities.FileStorage;
import com.example.onlinecourses.service.FileStorageService;
import com.example.onlinecourses.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileStorageService fileStorageService;
    private final FileMapper mapper;


    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        FileStorage fileStorage=fileStorageService.upload(file);
        return ResponseEntity.ok(mapper.mapToFileDto(fileStorage));
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        FileStorage file = fileStorageService.getByFileId(fileId);
        return FileUtils.returnFile(file);
    }
}
