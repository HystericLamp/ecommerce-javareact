package com.ecommerce.bcruz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;

@Service
public class UserService
{
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public User createUser(User newUser)
	{
		return userRepository.save(newUser);
	}
	
	public User getUserById(Long id)
	{
		return userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public User getUserByEmail(String email)
	{
		return userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	public User updateUser(Long id, User updatedUser)
	{
		User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);
	}
	
	public void deleteUserById(Long id)
	{
		User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
	}
}
