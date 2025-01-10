package com.firstproject.demo.service;

import com.firstproject.demo.dto.EnrollCourseDto;
import com.firstproject.demo.expetion.ResourceNotFoundException;
import com.firstproject.demo.model.Course;
import com.firstproject.demo.model.Student;
import com.firstproject.demo.repository.CourseRepository;
import com.firstproject.demo.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private CourseService courseService;



    @Test
    void getAll() {

        List<Course> courseList = new ArrayList<>();
        Course course = new Course();
        course.setCourseId(1L);
        course.setDuration("1month");
        course.setName("Java");
        course.setPrice(200d);
        courseList.add(course);

        Mockito.when(courseRepository.findAll()).thenReturn(courseList);

        List<Course> result = courseService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(courseList.size(), result.size());
        Assertions.assertEquals(courseList.get(0).getCourseId(), result.get(0).getCourseId());
        Assertions.assertEquals(courseList.get(0).getDuration(), result.get(0).getDuration());
        Assertions.assertEquals(courseList.get(0).getName(), result.get(0).getName());
        Assertions.assertEquals(courseList.get(0).getPrice(), result.get(0).getPrice());

    }

    @Test
    void getById() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setDuration("1month");
        course.setName("Java");
        course.setPrice(200d);

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course result = courseService.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getPrice(), course.getPrice());

    }

    @Test
    void getById_WhenNotFound() {
        Mockito.when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
            courseService.getById(1L);
        });

        Assertions.assertEquals("Course not found", exception.getLocalizedMessage());

    }

    @Test
    void save() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setDuration("1month");
        course.setName("Java");
        course.setPrice(200d);

        Mockito.when(courseRepository.save(course)).thenReturn(course);

        Course result = courseService.save(course);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getDuration(), course.getDuration());
    }

    @Test
    void update() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setDuration("1month");
        course.setName("Java");
        course.setPrice(200d);
        Mockito.when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course result = courseService.update(course, 1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getName(), course.getName());
    }

    @Test
    void deleteById() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setDuration("1month");
        course.setName("Java");
        course.setPrice(200d);
        Mockito.when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));

        String result = courseService.deleteById(1L);

        Mockito.verify(courseRepository, Mockito.times(1)).deleteById(1L);

        Assertions.assertEquals(result, "Course deleted successfully");
    }

    @Test
    void enrollCourse() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setDuration("1month");
        course.setName("Java");
        course.setPrice(200d);

        Student student = new Student();
        student.setStudentId(1L);
        student.setAge(20);
        student.setName("Amla");

        Mockito.when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        Mockito.when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        course.getStudents().add(student);
        student.getCourses().add(course);

        Mockito.when(courseRepository.save(course)).thenReturn(course);
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        Student result = courseService.enrollCourse(new EnrollCourseDto(1L, 1L));

        Assertions.assertNotNull(result);

        Assertions.assertEquals(student.getName(), result.getName());

    }
}