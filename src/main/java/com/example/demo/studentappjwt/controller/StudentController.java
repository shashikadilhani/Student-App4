package com.example.demo.studentappjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.studentappjwt.entity.AuthenticationRequest;
import com.example.demo.studentappjwt.entity.AuthenticationResponse;
import com.example.demo.studentappjwt.entity.Student;
import com.example.demo.studentappjwt.services.MyUserDetailsService;
import com.example.demo.studentappjwt.services.StudentService;
import com.example.demo.studentappjwt.util.JwtUtil;
 
@RestController
public class StudentController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	private StudentService studentService;
	
	public  StudentController(StudentService theStuedntService) {
		
		studentService = theStuedntService;
	} 
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello world ";
	}
	
	@GetMapping("/getstudents")
	public List<Student> findAll(){
		return studentService.getStudentList();
	}
	
	@GetMapping("/updatestudents/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		Student theEmployee = studentService.getStudent(studentId);
		
		if (theEmployee == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}
		
		return theEmployee;
	}
	
	@PutMapping("/savestudents")
	public Student updateStudent(@RequestBody Student theStudent) {
		
		studentService.save(theStudent);
		
		return theStudent;
	}
	
	
	@DeleteMapping("/deletestudents/{studentId}")
	public String deleteEmployee(@PathVariable int studentId) {
		
		Student tempStudent = studentService.getStudent(studentId);
		
		// throw exception if null
		
		if (tempStudent == null) {
			throw new RuntimeException("Employee id not found - " + studentId);
		}
		
		studentService.delete(studentId);
		
		return "Deleted student id - " + studentId;
	}

	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					);
		}
		
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect usename or password", e);
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		 
	}	

}
