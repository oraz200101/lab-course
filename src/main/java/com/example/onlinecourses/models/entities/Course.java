package com.example.onlinecourses.models.entities;

import com.example.onlinecourses.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses", schema = "lab_course")
public class Course extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "subscription")
    private Boolean subscription;

    @Column(name = "rating")
    private BigDecimal rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private CourseLanguage language;

    @Column(name = "total_hours")
    private Long totalHours;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id")
    private FileStorage image;

    @CreationTimestamp
    @Column(name = "date_time_create", updatable = false)
    private LocalDateTime dateTimeCreate;

    @UpdateTimestamp
    @Column(name = "date_time_update")
    private LocalDateTime dateTimeUpdate;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Objective> objectives;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Section> sections;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;


}
