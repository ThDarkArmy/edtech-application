package com.firstproject.demo.controller;


import com.firstproject.demo.dto.StudentDto;
import com.firstproject.demo.model.Student;
import com.firstproject.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return status(HttpStatus.OK).body(studentService.getAll());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return status(HttpStatus.OK).body(studentService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody StudentDto studentDto){
        return status(HttpStatus.CREATED).body(studentService.add(studentDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> add(@RequestBody Student student, @PathVariable Long id){
        return status(HttpStatus.OK).body(studentService.update(student, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return status(HttpStatus.OK).body(studentService.delete(id));
    }
}

/*
* In Rest APIs
* */
