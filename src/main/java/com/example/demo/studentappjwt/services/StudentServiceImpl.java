package com.example.demo.studentappjwt.services;


import com.example.demo.studentappjwt.dao.StudentRepository;
import com.example.demo.studentappjwt.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository theStudentRepository ) {

        studentRepository = theStudentRepository;
    }

    @Override
    public List<Student> getStudentList() {

        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(int Id) {
        Optional<Student> result = studentRepository.findById(Id);

        Student theStudent = null;

        if (result.isPresent()) {
            theStudent = result.get();
        }
        else {

            throw new RuntimeException("Did not find student id - " +Id);
        }

        return theStudent;
    }

    @Override
    public void save(Student theStudent) {
        studentRepository.save(theStudent);

    }

    @Override
    public void delete(int Id) {
        studentRepository.deleteById(Id);

    }

}
