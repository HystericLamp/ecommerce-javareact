package com.ecommerce.bcruz.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;

@Service
public class UserService
{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
	{
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public boolean login(String email, String password)
	{	
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(userOptional.isPresent())
		{
			return false;
		}
		
		User user = userOptional.get();
		
		return passwordEncoder.matches(password, user.getPassword());
	}
}
