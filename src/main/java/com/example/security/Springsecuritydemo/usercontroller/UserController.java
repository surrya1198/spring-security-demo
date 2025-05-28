package com.example.security.Springsecuritydemo.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.Springsecuritydemo.dto.UserDto;
import com.example.security.Springsecuritydemo.entity.UserAuthRequest;
import com.example.security.Springsecuritydemo.entity.UserDetailsInfos;
import com.example.security.Springsecuritydemo.userservice.JwTservice;
import com.example.security.Springsecuritydemo.userservice.UserService;

@RestController
@RequestMapping("/api/public")
public class UserController {

	@Autowired
	UserService userservice;

	@Autowired
	AuthenticationManager auth;
	
	@Autowired
	JwTservice jwtservice;

	@PostMapping("/genratetoken")
	public String authenticate(@RequestBody UserAuthRequest user) {

		Authentication authentication = auth
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		if(authentication.isAuthenticated()) {
		return jwtservice.genrateToken(user);
		}
		throw new UsernameNotFoundException("user does not exist");
		

	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserAuthRequest user) {
		return ResponseEntity.ofNullable(userservice.register(user));

	}

}
