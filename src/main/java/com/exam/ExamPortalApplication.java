package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	@Autowired
	private UserService uservice;
	
	@Autowired
	private BCryptPasswordEncoder brcyptencoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);

		
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Code");
		
		
//
//		try {
//		
//		 User user=new User();
//		 user.setFirstName("Tushar");
//		 		user.setLastName("Mathur");
//		 		user.setUserName("Tushar20");
//	user.setPassword(this.brcyptencoder.encode("abc"));
//	user.setEmail("abc@gmail");
//user.setProfile("default.png");	
//	
//	Role role=new Role();
//	role.setRoleId(44L);
//	role.setRoleName("ADMIN");
//	
//	
//Set<UserRole>userroleSet=new HashSet<>();
//UserRole role2 = new UserRole();
//role2.setRole(role);
//role2.setUser(user);
//userroleSet.add(role2);
//
//
//User user2 = this.uservice.createUser(user, userroleSet);
//System.out.println(user2.getUserName());
//	}catch (UserFoundException e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
		
	}
	
	}

