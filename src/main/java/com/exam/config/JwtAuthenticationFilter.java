package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.service.Impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		final String header = request.getHeader("Authorization");
	System.out.println(header);
	String userName=null;
	String jwtToken=null;
	
	if(header!=null && header.startsWith("Bearer")) {
	jwtToken=header.substring(7);
	try {
	 userName = this.jwtutil.extractUsername(jwtToken);
	
	}
	catch (ExpiredJwtException e) {
		e.printStackTrace();
		System.out.println("jwt token is expired");
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println("error");
	}
	
	}else {
		System.out.println("Invalid token");
	}
	
	
	//Validate
	if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		final UserDetails userdetails=this.userDetailsService.loadUserByUsername(userName);
if(this.jwtutil.validateToken(jwtToken, userdetails)) {
	//TOken is valid
	
	UsernamePasswordAuthenticationToken AuthenticationToken=new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
	AuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
}
	}else {
		System.out.println("Token is not valid");
	}
		
	filterChain.doFilter(request, response);
	}

}
