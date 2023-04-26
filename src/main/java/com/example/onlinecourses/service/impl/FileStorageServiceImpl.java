package com.example.onlinecourses.service.impl;

import com.example.onlinecourses.exception.domain.CustomValidationException;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.FileStorage;
import com.example.onlinecourses.repository.CourseRepository;
import com.example.onlinecourses.repository.FileStorageRepository;
import com.example.onlinecourses.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {
    private final FileStorageRepository fileStorageRepository;

    @Override
    public FileStorage upload(MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new RuntimeException();
            }
            FileStorage fileStorage = new FileStorage(fileName, multipartFile.getBytes(), multipartFile.getContentType(), multipartFile.getSize(), new Date());
            fileStorage=fileStorageRepository.save(fileStorage);
            return fileStorage;
        } catch (IOException ex) {
            throw new RuntimeException("Не удалось сохранить файл Пожалуйста, попробуйте еще раз!");
        }
    }

    @Override
    public FileStorage getByFileId(String fileId) {
        return fileStorageRepository.findById(fileId).orElseThrow(() -> new RuntimeException("file not found by id"));
    }
}
