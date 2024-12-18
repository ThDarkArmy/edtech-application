package com.firstproject.demo.dto;

import jakarta.validation.constraints.*;

public class StudentDto {

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 5, max = 20, message = "Name must be between 5 and 20")
    private String name;

    @Min(value = 4, message = "Minimum age must be 4")
    @Max(value = 35, message = "Maximum age must be 35")
    private int age;

    @NotNull(message = "Teacher id should not be null")
    private Long teacherId;

    public StudentDto() {
    }

    public StudentDto(String name, int age, Long teacherId) {
        this.name = name;
        this.age = age;
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
