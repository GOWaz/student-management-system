package com.example.student.management.system.teacher;

import com.example.student.management.system.course.Course;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Teacher")
@Table(name = "teacher",
        uniqueConstraints = {@UniqueConstraint(name = "teacher_email_unique",
                columnNames = "email")})
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence")
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "specialty",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String specialty;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Course> courses;
}
