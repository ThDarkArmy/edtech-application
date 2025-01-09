package com.firstproject.demo.controller;

import com.firstproject.demo.dto.LoginRequest;
import com.firstproject.demo.dto.LoginResponse;
import com.firstproject.demo.dto.MailBodyDto;
import com.firstproject.demo.dto.UserDto;
import com.firstproject.demo.model.User;
import com.firstproject.demo.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        return status(200).body(userService.getAll());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return status(200).body(userService.getById(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@ModelAttribute UserDto userDto) throws IOException {
        return status(201).body(userService.signUp(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return status(200).body(userService.login(loginRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id){
        return status(200).body(userService.update(user, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return status(200).body(userService.delete(id));
    }

    @PostMapping("/send-mail")
    public ResponseEntity<?> sendMail(@RequestBody MailBodyDto mailBodyDto) throws MessagingException {
        return status(200).body(userService.sendMail(mailBodyDto));
    }
}
