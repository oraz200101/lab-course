package com.example.onlinecourses.models.entities;

import com.example.onlinecourses.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "tags", schema = "lab_course")
public class Tag extends BaseEntity {
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Course> courses;
}
