package com.example.student.management.system.student;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequest {
    private String name;
    private String email;
    private LocalDate dob;
}
