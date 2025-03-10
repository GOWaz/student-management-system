package com.example.student.management.system.course;

import lombok.Data;

@Data
public class CourseRequest {
    private String courseName;
    private String description;
    private Long teacherId;
}
