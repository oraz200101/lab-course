package com.example.onlinecourses.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FileDto {
    private String id;
    private String name;
    private String contentType;
    private Long size;
    private String url;
    private Date uploadDate;
}
