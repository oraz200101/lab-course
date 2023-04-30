package com.example.onlinecourses.repository;

import com.example.onlinecourses.models.entities.UserCourseLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCourseLinkRepository extends JpaRepository<UserCourseLink, Long> {
    @Query("select (count(u) > 0) from UserCourseLink u where u.course.id = ?1 and u.user.id = ?2")
    boolean existsByCourse_IdAndUser_Id(Long courseId, Long userId);

}
