package com.firstproject.demo.service;

import com.firstproject.demo.model.Teacher;
import com.firstproject.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

    public Teacher addTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
}
