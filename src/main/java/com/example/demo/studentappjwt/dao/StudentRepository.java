package com.example.demo.studentappjwt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.studentappjwt.entity.Student;

 

public interface StudentRepository extends JpaRepository<Student, Integer> {

	 
		
		 
}
