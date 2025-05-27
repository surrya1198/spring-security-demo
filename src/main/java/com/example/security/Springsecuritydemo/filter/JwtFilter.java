package com.example.security.Springsecuritydemo.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.security.Springsecuritydemo.userservice.JwTservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtFilter extends OncePerRequestFilter {

	public UserDetailsService userdetailservice;
	public JwTservice jwtservice;

	public JwtFilter(UserDetailsService userDetailService, JwTservice service) {
		this.userdetailservice = userDetailService;
		this.jwtservice = service;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
			String token = request.getHeader("Authorization");
			// token = token.replace("Bearer", "");
			// String username = jwtservice.getUserName(token);

			UserDetails userDetails = userdetailservice.loadUserByUsername("test");
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(token, userDetails.getPassword());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			filterChain.doFilter(request, response);
		}
	



}
