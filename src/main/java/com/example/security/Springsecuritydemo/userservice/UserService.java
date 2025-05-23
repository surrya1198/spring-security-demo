package com.example.security.Springsecuritydemo.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.Springsecuritydemo.dto.UserDto;
import com.example.security.Springsecuritydemo.entity.UserInfo;
import com.example.security.Springsecuritydemo.repo.UserRepo;
import com.example.security.Springsecuritydemo.securityconfigures.UserInfoDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	public UserDto addUser(UserInfo userInfo) {

		UserDto userDto = new UserDto();
		userDto.setEmail(userInfo.getEmail());
		userDto.setName(userInfo.getUsername());
		userDto.setRoles(userInfo.getRoles());
		userRepo.save(userInfo);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
        Optional<UserInfo> userDetail = userRepo.findByEmail(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

}
