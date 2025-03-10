package com.example.student.management.system.teacher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        if (teacherRepository.existsById(id)) {
            return teacherRepository.findById(id);
        } else {
            throw new RuntimeException("Teacher not found");
        }
    }

    public Teacher create(TeacherRequest teacherRequest) {
        if (teacherRepository.findByName(teacherRequest.getName()).isPresent()) {
            throw new RuntimeException("Teacher already exists");
        }
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.getName());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setSpecialty(teacherRequest.getSpecialty());
        return teacherRepository.save(teacher);
    }

    public Teacher update(Long id, TeacherRequest teacherRequest) {
        return teacherRepository.findById(id).map(
                teacher -> {
                    teacher.setName(teacherRequest.getName());
                    teacher.setEmail(teacherRequest.getEmail());
                    teacher.setSpecialty(teacherRequest.getSpecialty());
                    return teacherRepository.save(teacher);
                }
        ).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public void delete(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } else {
            throw new RuntimeException("Teacher not found");
        }
    }
}
