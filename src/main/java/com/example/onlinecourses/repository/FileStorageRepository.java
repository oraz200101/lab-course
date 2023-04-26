package com.example.onlinecourses.repository;

import com.example.onlinecourses.models.entities.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, String> {
}
