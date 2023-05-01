package com.example.onlinecourses.models.entities;

import com.example.onlinecourses.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments", schema = "lab_course")
public class Comment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "rating")
    @NotNull
    private BigDecimal rating;

    @Column(name = "date_time_create")
    @CreationTimestamp
    private LocalDateTime dateTimeCreate;
}
