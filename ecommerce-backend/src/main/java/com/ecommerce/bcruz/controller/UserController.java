package com.ecommerce.bcruz.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController
{
	private final UserService userService;
	
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping("/by-id/{id}")
	public User getUserById(@PathVariable Long id) 
	{
        return userService.getUserById(id);
    }
	
	@GetMapping("/by-email")
	public User getUserByEmail(@RequestParam String email) 
	{
        return userService.getUserByEmail(email);
    }
	
	@PostMapping("/newUser")
	public User registerNewUser(@RequestBody User newUser)
	{
		return userService.createUser(newUser);
	}
	
	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user)
	{
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id)
	{
		userService.deleteUserById(id);
	}
}
