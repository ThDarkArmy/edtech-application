package com.firstproject.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
public class DemoController {

    @GetMapping
    public ResponseEntity<String> getDemoPage(){
//        return "Demo Application";
        return status(HttpStatus.BAD_REQUEST).body("Demo Application");
    }
}
