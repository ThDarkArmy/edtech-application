package com.firstproject.demo.service;

import com.firstproject.demo.dto.EnrollCourseDto;
import com.firstproject.demo.expetion.ResourceNotFoundException;
import com.firstproject.demo.model.Course;
import com.firstproject.demo.model.Student;
import com.firstproject.demo.repository.CourseRepository;
import com.firstproject.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course getById(Long id){
        return courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course not found"));
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public Course update(Course course, Long id){
        courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course not found"));
        course.setCourseId(id);
        return courseRepository.save(course);
    }

    public String deleteById(Long id){
        courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course not found"));
        courseRepository.deleteById(id);
        return "Course deleted successfully";
    }

    public Student enrollCourse(EnrollCourseDto enrollCourseDto) {
        Student student = studentRepository.findById(enrollCourseDto.getStudentId()).orElseThrow(()-> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(enrollCourseDto.getCourseId()).orElseThrow(()-> new RuntimeException("Course not found"));

        course.getStudents().add(student);
        student.getCourses().add(course);
        courseRepository.save(course);
        return studentRepository.save(student);
    }
}
