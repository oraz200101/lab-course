package com.example.onlinecourses.models.entities;

import com.example.onlinecourses.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "categories", schema = "lab_course")
public class Category extends BaseEntity {
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Course> courses;
}
