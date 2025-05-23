package com.example.security.Springsecuritydemo.securityconfigures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.Springsecuritydemo.filter.JwtFilter;
import com.example.security.Springsecuritydemo.userservice.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	UserService userService;
	JwtFilter jwtFilter;

	public SecurityConfig(UserService userService, JwtFilter jwtFilter) {
		this.userService = userService;
		this.jwtFilter = jwtFilter;
	}

	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").disable());

		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/public/user/adduser","/h2-console/**").permitAll()
				
				.anyRequest().authenticated()

		)

				.authenticationProvider(authProvider())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

		;

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
