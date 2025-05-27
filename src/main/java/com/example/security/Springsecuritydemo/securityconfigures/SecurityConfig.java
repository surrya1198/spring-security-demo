package com.example.security.Springsecuritydemo.securityconfigures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.Springsecuritydemo.filter.JwtFilter;
import com.example.security.Springsecuritydemo.userservice.UserDetService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	UserDetService user;

	public SecurityConfig(UserDetService usee) {
		this.user = usee;

	}

	@Bean
	protected SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/public/**", "/h2-console/**").permitAll() // ✅
						// public
						.anyRequest().authenticated() // 🔒 authenticated routes
				).authenticationProvider(authProvider()).userDetailsService(user)
				.headers(headers -> headers.frameOptions(fr -> fr.disable()).disable()).formLogin(withDefaults())
				.httpBasic(withDefaults())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();

	}

	@Bean
	protected AuthenticationProvider authProvider() {
		DaoAuthenticationProvider userDAO = new DaoAuthenticationProvider();
		userDAO.setUserDetailsService(this.user);
		userDAO.setPasswordEncoder(passwordEncoder());
		return userDAO;

	}

//	@Bean
//	protected AuthenticationManager authenticationmanger(AuthenticationConfiguration config) throws Exception {
//
//		return config.getAuthenticationManager();
//	}

	@Bean
	protected PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

//	@Bean
//	@Primary
//	public UserDetailsService user() {
//
//		UserDetails u = User.withDefaultPasswordEncoder().username("test").password("test").roles("admin").build();
//
//		UserDetails u2 = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("admin").build();
//
//		return new InMemoryUserDetailsManager(u, u2);
//	}

//	@Bean
	public UserDetailsService user() {

		return this.user;

	}

}
