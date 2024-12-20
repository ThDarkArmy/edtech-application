package com.firstproject.demo.service;

import com.firstproject.demo.dto.LoginRequest;
import com.firstproject.demo.dto.LoginResponse;
import com.firstproject.demo.expetion.ResourceNotFoundException;
import com.firstproject.demo.expetion.UserAlreadyExistsException;
import com.firstproject.demo.model.User;
import com.firstproject.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    public User signUp(User user){
        // TODO: update this method
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) throw new UserAlreadyExistsException("User with given email already exists");
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest){
        // TODO: update this method
        return new LoginResponse();
    }

    public User update(User user, Long id){
        User user1 = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        user.setId(id);
        return userRepository.save(user);
    }

    public String delete(Long id){
        userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
