package com.example.demo.studentappjwt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.studentappjwt.dao.UserRepository;
import com.example.demo.studentappjwt.entity.User;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(userId);

		if(user==null){
			throw new UsernameNotFoundException("the user is not found");
		}else{

			String role = user.getRole();
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(role));

			return new org.springframework.security.core.userdetails.User(user.getUsername(),encoder.encode(user.getPassword()), authorities);

		}
	}

	private UserDetails toUserDetails(UserObject userObject) {
		return org.springframework.security.core.userdetails.User.withUsername(userObject.name)
				.password(userObject.password)
				.roles(userObject.role).build();
	}
	private static class UserObject {
		private String name;
		private String password;
		private String role;

		public UserObject(String name, String password, String role) {
			this.name = name;
			this.password = password;
			this.role = role;
		}
	}


	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public User getUser(int Id) {
		Optional<User> result = userRepository.findById(Id);

		User theUser= null;

		if (result.isPresent()) {
			theUser = result.get();
		}
		else {

			throw new RuntimeException("Did not find student id - " +Id);
		}

		return theUser;
	}

	@Override
	public void delete(int id) {

		userRepository.deleteById(id);

	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}



}
