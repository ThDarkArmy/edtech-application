package com.firstproject.demo.service;

import ch.qos.logback.core.util.StringUtil;
import com.firstproject.demo.config.JwtTokenProvider;
import com.firstproject.demo.dto.LoginRequest;
import com.firstproject.demo.dto.LoginResponse;
import com.firstproject.demo.dto.MailBodyDto;
import com.firstproject.demo.dto.UserDto;
import com.firstproject.demo.expetion.ResourceNotFoundException;
import com.firstproject.demo.expetion.UserAlreadyExistsException;
import com.firstproject.demo.model.User;
import com.firstproject.demo.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static String BASE_URL = "http://localhost:8000";

    private Path fileStoragePath;

    public UserService() {
        try{
            fileStoragePath = Paths.get("src\\main\\resources\\static\\fileStorage");
            Files.createDirectories(fileStoragePath);
        }catch(IOException exception){
            throw new RuntimeException("Issue in creating directory");
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MailSenderService mailSenderService;

//    @Autowired
//    private S3Service s3Service;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    public User signUp(UserDto userDto) throws IOException {
        // TODO: update this method
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        if(userOptional.isPresent()) throw new UserAlreadyExistsException("User with given email already exists");


        // file uploading
        String fileName = StringUtils.cleanPath(userDto.getProfilePicture().getOriginalFilename());
        fileName = fileName.replace(" ", "");

        Path filePath = Paths.get(fileStoragePath+"\\"+fileName);

        try{
            Files.copy(userDto.getProfilePicture().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException exception){
            exception.printStackTrace();
            throw new RuntimeException("issue in uploading file");
        }

        String url = "";//s3Service.uploadFile(userDto.getProfilePicture());
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setContactNumber(userDto.getContactNumber());
        user.setRole(userDto.getRole());
        user.setProfilePicUrl(url);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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

    public Object sendMail(MailBodyDto mailBodyDto) throws MessagingException {
        mailSenderService.sendMail(mailBodyDto.getEmail(), mailBodyDto.getSubject(), mailBodyDto.getMailBody());
        return "Email sent successfully";
    }
}
