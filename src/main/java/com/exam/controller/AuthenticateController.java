package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtils;
import com.exam.entity.JWTRequest;
import com.exam.entity.JWTResponse;
import com.exam.entity.User;
import com.exam.helper.UserNotFoundException;
import com.exam.service.Impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	///generate token 
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception{
	try {
	authenticate(jwtRequest.getUserName(),jwtRequest.getPassword());	
	}catch (UserNotFoundException e) {
		e.printStackTrace();
		throw new Exception ("User Not found");
	}
	//Authenticate User
	
	UserDetails userdetail = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
	String token = this.jwtUtils.generateToken(userdetail);
	return ResponseEntity.ok(new JWTResponse(token));
	}
	
	
	
	
	
	
	private void authenticate(String userName,String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
		}catch (DisabledException e) {
			throw new Exception("USER DISABLED");
			}catch (BadCredentialsException e) {
				throw new Exception("Invalid Credentials "+e.getMessage() );
			}
	}
	
	
	//Return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User)this.userDetailsService.loadUserByUsername(principal.getName());
	}
}
