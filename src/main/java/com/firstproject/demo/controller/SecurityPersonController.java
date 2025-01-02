package com.firstproject.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/security-persons")
public class SecurityPersonController {

    @GetMapping
    public ResponseEntity<?> securityPersons(){
        return status(200).body("Security person controller");
    }
}
