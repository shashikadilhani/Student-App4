package com.example.demo.studentappjwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.studentappjwt.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    void deleteById(int id);
    User save(User user);
    Optional<User> findById(int id);
	 
		
		 
}
