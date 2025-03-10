package com.example.student.management.system.enrollment;

import com.example.student.management.system.course.Course;
import com.example.student.management.system.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Enrollment")
@Table(name = "enrollement")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @SequenceGenerator(
            name = "enrollment_sequence",
            sequenceName = "enrollment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "enrollment_sequence")
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "enrollmentDate",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;
}
