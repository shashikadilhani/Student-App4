package com.example.demo.studentappjwt.dao;


import com.example.demo.studentappjwt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    void deleteById(int id);

   Optional<Student> findById(int id);



}
