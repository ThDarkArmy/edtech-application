package com.firstproject.demo.service;

import com.firstproject.demo.model.Course;
import com.firstproject.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course getById(Long id){
        return courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course not found"));
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public Course update(Course course, Long id){
        courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course not found"));
        course.setCourseId(id);
        return courseRepository.save(course);
    }

    public String deleteById(Long id){
        courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course not found"));
        courseRepository.deleteById(id);
        return "Course deleted successfully";
    }
}
