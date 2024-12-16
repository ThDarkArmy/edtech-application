package com.firstproject.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;
    private String duration;
    private Double price;

    public Course() {
    }

    public Course(Long courseId, String name, String duration, Double price) {
        this.courseId = courseId;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                '}';
    }
}
