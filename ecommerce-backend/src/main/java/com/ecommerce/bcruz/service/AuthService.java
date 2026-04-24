package com.ecommerce.bcruz.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.dto.AuthResponse;
import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;

@Service
public class AuthService
{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder)
	{
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public AuthResponse login(String email, String password)
	{	
		User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Email does not exist"));
		
		if(!passwordEncoder.matches(password, user.getPassword())) 
		{
	        throw new RuntimeException("Wrong Password");
	    }
		
		return new AuthResponse(
	            user.getId(),
	            user.getEmail()
	    );
	}
}
