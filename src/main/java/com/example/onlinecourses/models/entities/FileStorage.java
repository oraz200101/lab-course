package com.example.onlinecourses.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@Entity(name = "files")
@Table(schema ="lab_course")
@NoArgsConstructor
public class FileStorage {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @Lob
    private byte[] data;
    private String contentType;
    private String link;
    private Long size;
    private Date uploadDate;

    public FileStorage(String name, byte[] data, String contentType, Long size, Date uploadDate) {
        this.name = name;
        this.data = data;
        this.contentType = contentType;
        this.size = size;
        this.uploadDate = uploadDate;
    }
}
