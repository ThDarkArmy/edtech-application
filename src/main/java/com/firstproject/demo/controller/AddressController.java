package com.firstproject.demo.controller;

import com.firstproject.demo.dto.AddressDto;
import com.firstproject.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return status(200).body(addressService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody AddressDto addressDto){
        return status(201).body(addressService.save(addressDto));
    }

}
