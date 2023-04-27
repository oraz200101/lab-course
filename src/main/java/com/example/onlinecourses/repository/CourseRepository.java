package com.example.onlinecourses.repository;

import com.example.onlinecourses.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.id = ?1 and c.author.id = ?2")
    Optional<Course> findByIdAndAuthor_Id(Long courseId, Long authorId);
}
