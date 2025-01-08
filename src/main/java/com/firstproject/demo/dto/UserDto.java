package com.firstproject.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
    private String name;
    private String email;
    private String contactNumber;
    private String role;
    private String password;

    private MultipartFile profilePicture;

    public UserDto() {
    }

    public UserDto(String name, String email, String contactNumber, String role, String password, MultipartFile profilePicture) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.role = role;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", profilePicture=" + profilePicture +
                '}';
    }
}
