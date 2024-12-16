package com.firstproject.demo.repository;

import com.firstproject.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
