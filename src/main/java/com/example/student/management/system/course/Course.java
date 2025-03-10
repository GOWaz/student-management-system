package com.example.student.management.system.course;

import com.example.student.management.system.enrollment.Enrollment;
import com.example.student.management.system.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_sequence")
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Enrollment> enrollmentList;
}
