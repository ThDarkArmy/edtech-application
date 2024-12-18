package com.firstproject.demo.service;

import com.firstproject.demo.dto.StudentDto;
import com.firstproject.demo.expetion.ResourceNotFoundException;
import com.firstproject.demo.model.Student;
import com.firstproject.demo.model.Teacher;
import com.firstproject.demo.repository.StudentRepository;
import com.firstproject.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student getById(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found"));
    }

    public Student add(StudentDto studentDto){
        Teacher teacher = teacherRepository.findById(studentDto.getTeacherId()).orElseThrow(()-> new ResourceNotFoundException("Teacher not found"));
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setTeacher(teacher);
        student = studentRepository.save(student);
        teacher.getStudents().add(student);
        teacherRepository.save(teacher);
        return student;
    }

    public Student update(Student student, Long id){
        Student student1 = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found"));
        student.setStudentId(id);
        return studentRepository.save(student);
    }

    public String delete(Long id){
        studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
}
