package com.firstproject.demo.controller;

import com.firstproject.demo.model.Teacher;
import com.firstproject.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return status(200).body(teacherService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Teacher teacher){
        return status(200).body(teacherService.addTeacher(teacher));
    }
}
