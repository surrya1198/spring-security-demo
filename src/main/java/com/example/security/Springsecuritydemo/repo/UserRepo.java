package com.example.security.Springsecuritydemo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.Springsecuritydemo.entity.UserDetailsInfos;

@Repository
public interface UserRepo extends JpaRepository<UserDetailsInfos, Long> {
	Optional<UserDetailsInfos> findByEmail(String email); // Use 'email' if that is the correct field for login

	
}
