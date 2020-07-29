package com.example.demo.studentappjwt.dao;

import com.example.demo.studentappjwt.entity.Parent;
import com.example.demo.studentappjwt.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

public interface ParentReository extends JpaRepository<Parent,Long> {

    List<Parent> findByStudent(Student student, Sort sort);
}
