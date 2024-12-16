package com.firstproject.demo.repository;

import com.firstproject.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name); // select * from student where name="name";
    List<Student> findByAge(int age); // select * from student where age=age;
    List<Student> findByNameAndAge(String name, int age); // select * from student where name=name and age=age;

//    @Query("Select * from student where name=?")
//    List<Student> findStudentByCustomQuery(String name);
}
