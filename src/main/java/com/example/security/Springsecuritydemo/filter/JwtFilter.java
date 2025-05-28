package com.example.security.Springsecuritydemo.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.security.Springsecuritydemo.userservice.JwTservice;
import com.example.security.Springsecuritydemo.userservice.UserDetService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "expired")
public class JwtFilter extends OncePerRequestFilter {

	public JwTservice jwtservice;
	public UserDetService user;

	public JwtFilter(UserDetService userDetailService, JwTservice service) {
		this.user = userDetailService;
		this.jwtservice = service;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String BearerToken = request.getHeader("Authorization");
		String userName = null;
//		try {
//			if (BearerToken != null && !(jwtservice.validateToken(BearerToken.substring(7)))) {
//
//				throw new ExpiredJwtException(null, jwtservice.getClaims(BearerToken.substring(7)), "token Expired");
//			}
//		} catch (Exception e) {
//			response.sendError(HttpStatus.FORBIDDEN.value(), "invalid token");
//			response.getWriter().write("TEST");
//		}
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
