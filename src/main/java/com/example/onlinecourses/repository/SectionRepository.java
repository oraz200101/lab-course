package com.example.onlinecourses.repository;

import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    @Query("select s from Section s where s.course = ?1")
    List<Section> findByCourse(Course course);

}
