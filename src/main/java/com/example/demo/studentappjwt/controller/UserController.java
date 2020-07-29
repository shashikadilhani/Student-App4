package com.example.demo.studentappjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.studentappjwt.entity.AuthenticationRequest;
import com.example.demo.studentappjwt.entity.AuthenticationResponse;
import com.example.demo.studentappjwt.entity.User;
import com.example.demo.studentappjwt.services.UserService;
import com.example.demo.studentappjwt.util.JwtUtil;


@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Qualifier("userServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	private UserService userService;
	
	public UserController(UserService theUserService) {
		
		userService = theUserService;
	}

	@RequestMapping(value="/userlist", method = RequestMethod.GET)
	public List<User> listUser(){
		return userService.findAll();
	}

	@GetMapping("/userlist/{userId}")
	public User getUser(@PathVariable int userId) {

		User theUser = userService.getUser(userId);

		if (theUser == null) {
			throw new RuntimeException("User id not found - " + userId);
		}

		return theUser;
	}

	@PutMapping("/saveuser")
	public User saveUser(@RequestBody User theUser) {

		userService.save(theUser);

		return theUser;
	}


	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id") int id){
		userService.delete(id);
		return "success";
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
