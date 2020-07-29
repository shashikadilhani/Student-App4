package com.example.demo.studentappjwt.entity;

public class AuthenticationRequest {

//	private int id;
//	private String email;
	private String username;
	
	private String password;
//	private String role;

	public AuthenticationRequest() {
		
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationRequest(String username, String password) {

		this.username = username;
		this.password = password;
	}


}
