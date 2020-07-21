package com.example.demo.studentappjwt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.studentappjwt.entity.Student;

 
 
public interface StudentService {
	
public List<Student> getStudentList();
	
	public Student getStudent(int Id);
	
	public void save(Student theStudent);
	
	public void delete(int Id);

}
