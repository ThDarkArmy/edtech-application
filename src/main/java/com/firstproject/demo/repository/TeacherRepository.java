package com.firstproject.demo.repository;

import com.firstproject.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
