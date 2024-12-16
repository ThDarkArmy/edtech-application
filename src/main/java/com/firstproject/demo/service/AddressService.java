package com.firstproject.demo.service;

import com.firstproject.demo.dto.AddressDto;
import com.firstproject.demo.model.Address;
import com.firstproject.demo.model.Student;
import com.firstproject.demo.repository.AddressRepository;
import com.firstproject.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    public Address save(AddressDto addressDto){
        Student student = studentRepository.findById(addressDto.getStudentId()).orElseThrow(()-> new RuntimeException("Student not found"));

        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setZipCode(addressDto.getZipCode());
        address.setStudent(student);
        address = addressRepository.save(address);

        student.setAddress(address);
        studentRepository.save(student);
        return address;
    }
}
