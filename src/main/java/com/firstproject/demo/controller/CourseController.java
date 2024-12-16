package com.firstproject.demo.controller;

import com.firstproject.demo.model.Course;
import com.firstproject.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return status(200).body(courseService.getAll());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return status(200).body(courseService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Course course){
        return status(201).body(courseService.save(course));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable Long id){
        return status(200).body(courseService.update(course, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return status(200).body(courseService.deleteById(id));
    }

}


