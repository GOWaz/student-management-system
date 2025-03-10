package com.example.student.management.system.course;

import com.example.student.management.system.teacher.Teacher;
import com.example.student.management.system.teacher.TeacherRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        if (courseRepository.existsById(id)) {
            return courseRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found");
        }
    }

    public Course save(CourseRequest request) {
        if (teacherRepository.existsById(request.getTeacherId())) {
            Teacher teacher = teacherRepository.findById(request.getTeacherId()).get();
            Course course = new Course();
            course.setTitle(request.getCourseName());
            course.setDescription(request.getDescription());
            course.setTeacher(teacher);
            return courseRepository.save(course);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");

    }

    public Course update(Long id, CourseRequest request) {
        return courseRepository.findById(id).map(course -> {
            course.setTitle(request.getCourseName());
            course.setDescription(request.getDescription());
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found");
        }
    }
}
