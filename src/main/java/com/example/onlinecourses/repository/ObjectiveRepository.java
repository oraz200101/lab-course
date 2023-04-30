package com.example.onlinecourses.repository;

import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
    @Query("select o from Objective o where o.course = ?1")
    List<Objective> findByCourse(Course course);

}
