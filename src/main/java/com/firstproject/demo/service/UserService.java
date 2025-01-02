package com.firstproject.demo.service;

import com.firstproject.demo.config.JwtTokenProvider;
import com.firstproject.demo.dto.LoginRequest;
import com.firstproject.demo.dto.LoginResponse;
import com.firstproject.demo.expetion.ResourceNotFoundException;
import com.firstproject.demo.expetion.UserAlreadyExistsException;
import com.firstproject.demo.model.User;
import com.firstproject.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User with email does not exists."));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return new LoginResponse(token, user);
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
