package com.example.security.Springsecuritydemo.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.security.Springsecuritydemo.entity.UserDetailsInfos;
import com.example.security.Springsecuritydemo.repo.UserRepo;
import com.example.security.Springsecuritydemo.securityconfigures.UserInfoDetails;

@Component
public class UserDetService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Optional<UserDetailsInfos> userDetail = userRepo.findByEmail(username); // Assuming 'email' is used as username

		// Converting UserInfo to UserDetails
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

}
