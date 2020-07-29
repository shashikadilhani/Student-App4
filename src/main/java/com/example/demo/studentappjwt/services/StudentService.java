package com.example.demo.studentappjwt.services;


import com.example.demo.studentappjwt.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudentList();

    public Student getStudent(int Id);

    public void save(Student theStudent);

    public void delete(int Id);
}
