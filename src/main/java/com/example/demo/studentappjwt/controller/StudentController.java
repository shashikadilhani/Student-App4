package com.example.demo.studentappjwt.controller;

import com.example.demo.studentappjwt.entity.Student;
import com.example.demo.studentappjwt.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService theStudentService) {

        studentService = theStudentService;

    }

    @GetMapping("/list")
    public List<Student> findAll(){
        return studentService.getStudentList();
    }

    @GetMapping("/list/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        Student theStudent = studentService.getStudent(studentId);

        if (theStudent == null) {
            throw new RuntimeException("Student id not found - " + studentId);
        }

        return theStudent;
    }

    @PutMapping("/save")
    public Student saveStudent(@RequestBody Student theStudent) {

        studentService.save(theStudent);

        return theStudent;
    }


    @DeleteMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {

        Student tempStudent = studentService.getStudent(studentId);

        if (tempStudent == null) {
            throw new RuntimeException("Student id not found - " + studentId);
        }

        studentService.delete(studentId);

        return "Deleted student id - " + studentId;
    }

}
