package com.example.onlinecourses.service;

import com.example.onlinecourses.models.entities.FileStorage;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    FileStorage upload(MultipartFile multipartFile);

    FileStorage getByFileId(String fileId);
}
