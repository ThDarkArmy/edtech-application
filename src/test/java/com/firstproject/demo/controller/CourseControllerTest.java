package com.firstproject.demo.controller;

import com.firstproject.demo.dto.EnrollCourseDto;
import com.firstproject.demo.model.Course;
import com.firstproject.demo.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private List<Course> courseList;

    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();

        courseList = new ArrayList<>();


        course = new Course(1L, "Java", "1 month", 50d);
        courseList.add(course);
    }

    @Test
    void getAll() throws Exception {
        when(courseService.getAll()).thenReturn(courseList);

        mockMvc.perform(get("/courses/all")).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());
        verify(courseService, times(1)).getAll();

    }

    @Test
    void getById() throws Exception {
        when(courseService.getById(1L)).thenReturn(course);
        mockMvc.perform(get("/courses/by-id/1"))
                .andExpect(jsonPath("$.name").value(course.getName()))
                .andExpect(jsonPath("$.duration").value(course.getDuration()))
                .andExpect(status().isOk());

        verify(courseService, times(1)).getById(1L);
    }

    @Test
    void save() throws Exception {
        when(courseService.save(any(Course.class))).thenReturn(course);
        mockMvc.perform(post("/courses/save").contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"1\", \"name\": \"Java\", \"duration\": \"1 month\", \"price\": \"50\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Java"));
        verify(courseService, times(1)).save(any(Course.class));
    }

    @Test
    void update() throws Exception {
        course.setName("Python");
        when(courseService.getById(1L)).thenReturn(course);
        when(courseService.update(any(Course.class), anyLong())).thenReturn(course);
        mockMvc.perform(put("/courses/update/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"name\": \"Python\", \"duration\": \"1 month\", \"price\": \"50\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Python"));
        verify(courseService, times(1)).update(any(Course.class), eq(1L));
    }

    @Test
    void deleteCourse() throws Exception {
        when(courseService.getById(1L)).thenReturn(course);
        when(courseService.deleteById(1L)).thenReturn("Course deleted successfully");
        mockMvc.perform(delete("/courses/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Course deleted successfully"));
        verify(courseService, times(1)).deleteById(1L);
    }

    @Test
    void enroll() {
    }
}
