package com.example.onlinecourses.repository;

import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.User;
import com.example.onlinecourses.models.enums.CourseHoursEnum;
import com.example.onlinecourses.models.enums.SortEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.id = ?1 and c.author.id = ?2")
    Optional<Course> findByIdAndAuthor_Id(Long courseId, Long authorId);

    @Query(value = "SELECT c FROM Course AS c " +
            " WHERE" +
            " (:rating IS NULL OR (:rating<=c.rating)) AND " +
            " (:hours IS NULL  " +
            " OR (:hours='NULL_TO_ONE' AND c.totalHours>0 AND c.totalHours<=1) " +
            " OR (:hours='ONE_TO_THREE' AND c.totalHours>1 AND c.totalHours<=3) " +
            " OR (:hours='THREE_TO_SIX' AND c.totalHours>3 AND c.totalHours<=6) " +
            " OR (:hours='SIX_TO_SEVENTEEN' AND c.totalHours>6 AND c.totalHours<=17) " +
            " OR (:hours='MORE_SEVENTEEN' AND c.totalHours>17))" +
            " ORDER BY CASE " +
            "WHEN (:sort='MOST_POPULAR') THEN c.buyCount " +
            "WHEN (:sort='HIGH_RATING') THEN c.rating END DESC "
    )
    List<Course> findByRating(
            @Param("rating") BigDecimal rating,
            @Param("hours") String hours,
            @Param("sort") String sort);

    @Query("select c from Course c inner join c.courseLinks courseLinks where c.id = ?1 and courseLinks.user.id = ?2")
    Optional<Course> findByIdAndCourseLinks_User_Id(Long id, Long id1);

    @Query("select c from Course c where c.author.id = ?1")
    List<Course> findByAuthor_Id(Long id);

    @Transactional
    @Modifying
    @Query("delete from Course c where c.id = ?1 and c.author.id = ?2")
    void deleteByIdAndAuthorId(Long id, Long authorId);

    @Query("select c from Course c where c.subscription = true")
    List<Course> findBySubscription();


}
