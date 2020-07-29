package com.example.demo.studentappjwt.services;

import java.util.List;

import com.example.demo.studentappjwt.entity.User;

 
 
public interface UserService {

	User save(User user);
	void delete(int id);
	List<User> findAll();
	public User getUser(int Id);

}
