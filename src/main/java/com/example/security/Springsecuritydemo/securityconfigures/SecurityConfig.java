package com.example.security.Springsecuritydemo.securityconfigures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security.Springsecuritydemo.userservice.UserService;

@EnableWebSecurity
public class SecurityConfig {

	UserService userService;

	public SecurityConfig(UserService userService) {
		this.userService = userService;
	}

	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests().requestMatchers("/public/user/**", "/h2").permitAll()

				.anyRequest().authenticated();

		return http.build();
	}

	@Bean
	protected AuthenticationProvider authProvider() {
		DaoAuthenticationProvider user = new DaoAuthenticationProvider();
		user.setUserDetailsService(userService);
		user.setPasswordEncoder(passwordEncoder());
		return user;

	}

	@Bean
	protected AuthenticationManager authenticationmanger(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}

	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
