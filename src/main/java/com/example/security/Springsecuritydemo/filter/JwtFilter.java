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

@Component
public class JwtFilter extends OncePerRequestFilter {

	public UserDetailsService userdetailservice;
	public JwTservice jwtservice;
	public UserDetailsService user;

	public JwtFilter(UserDetailsService userDetailService, JwTservice service) {
		this.userdetailservice = userDetailService;
		this.jwtservice = service;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String BearerToken = request.getHeader("Authorization");
		String userName = null;
		if (BearerToken != null && BearerToken.startsWith("Bearer")) {

			String token = BearerToken.substring(7);
			userName = jwtservice.getUserName(token);
		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = user.loadUserByUsername(userName);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			// since the authentication should know about the details we need to set the
			// below details
			token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			// finally set the token to the security context....
			SecurityContextHolder.getContext().setAuthentication(token);

		}
		filterChain.doFilter(request, response);
	}

}
