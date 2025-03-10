package com.example.student.management.system.enrollment;

import com.example.student.management.system.course.Course;
import com.example.student.management.system.course.CourseRepository;
import com.example.student.management.system.student.Student;
import com.example.student.management.system.student.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> findEnrollmentById(Long id) {
        if (enrollmentRepository.existsById(id)) {
            return enrollmentRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment Not Found");
        }
    }

    public Enrollment newEnrollment(Long studentId, Long courseId) {
        if (studentRepository.findById(studentId).isPresent() && courseRepository.findById(courseId).isPresent()) {
            Enrollment enrollment = new Enrollment();
            Student student = studentRepository.findById(studentId).get();
            Course course = courseRepository.findById(courseId).get();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setEnrollmentDate(LocalDate.now());
            return enrollmentRepository.save(enrollment);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student or Course Not Found");
    }

    public void deleteEnrollment(Long enrollmentId) {
        if (enrollmentRepository.existsById(enrollmentId)) {
            enrollmentRepository.deleteById(enrollmentId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment Not Found");
        }
    }
}
