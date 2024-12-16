package com.firstproject.demo.dto;

public class StudentDto {
    private String name;
    private int age;
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
