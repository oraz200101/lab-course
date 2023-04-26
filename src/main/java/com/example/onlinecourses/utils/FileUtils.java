package com.example.onlinecourses.utils;

import com.example.onlinecourses.models.entities.FileStorage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class FileUtils {
    public static ResponseEntity<Resource> returnFile(FileStorage fileStorage) {
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileStorage.getName() + "\"").
                body(new ByteArrayResource(fileStorage.getData()));
    }
}
