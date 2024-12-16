package com.firstproject.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String speciality;

    @OneToMany(mappedBy = "teacher")
    private Set<Student> students = new HashSet<>();

    public Teacher() {
    }

    public Teacher(Long id, String name, String speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
