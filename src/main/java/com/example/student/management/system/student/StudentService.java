package com.example.student.management.system.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        if (studentRepository.existsById(id)) {
            return studentRepository.findById(id);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    public Student create(StudentRequest request) {
        if (studentRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("Student already exists");
        }
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDob(request.getDob());
        return studentRepository.save(student);
    }

    public Student update(Long id, StudentRequest request) {
        return studentRepository.findById(id).map(
                student -> {
                    student.setName(request.getName());
                    student.setEmail(request.getEmail());
                    student.setDob(request.getDob());
                    return studentRepository.save(student);
                }
        ).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void delete(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found");
        }
    }
}
