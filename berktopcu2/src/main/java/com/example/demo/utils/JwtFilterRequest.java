package com.example.demo.utils;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.UserService;
import com.example.demo.utils.JwtUtils;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {


	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService userService;
	
	//https://www.youtube.com/watch?v=OxyBRjGfJsk 13:--
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	
		String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwtToken = authorizationHeader.substring(7);
			username = jwtUtils.extractUsername(jwtToken);
			
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails currentUserDetails = userService.loadUserByUsername(username);
			Boolean tokenValidated = jwtUtils.validateToken(jwtToken, currentUserDetails);
			if (tokenValidated) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(currentUserDetails, null);
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((jakarta.servlet.http.HttpServletRequest) request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
			filterChain.doFilter(request, response);
	}



	
}
