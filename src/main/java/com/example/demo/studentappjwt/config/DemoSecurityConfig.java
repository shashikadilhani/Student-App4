package com.example.demo.studentappjwt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.studentappjwt.filters.JwtRequestFilter;
import com.example.demo.studentappjwt.services.MyUserDetailsService;

 

@EnableWebSecurity
public class DemoSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	/*@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;*/
		
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(myUserDetailsService);
	 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/authenticate").permitAll().
			anyRequest().authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		/*http.authorizeRequests()
		.antMatchers("/students*").hasAnyRole( "ADMIN","STUDENT")
		.antMatchers("/updatestudents*").hasRole("ADMIN")
		.antMatchers("/savestudents*").hasRole("ADMIN")
		.antMatchers("/deletestudents").hasRole("ADMIN")
		.antMatchers("/students").hasAnyRole("ADMIN","STUDENT")
		.and()
		.formLogin()
			.loginPage()
			.permitAll()
		.and()
		.logout().permitAll()*/
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean()throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncorder() {
		return NoOpPasswordEncoder.getInstance();
	}


}
