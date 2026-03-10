package com.ecommerce.bcruz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/registerNewUser")
	public boolean registerNewUser()
	{
		
		return true;
	}
	
	@GetMapping("/updateUser")
	public boolean updateUser()
	{
		return true;
	}
	
	@GetMapping("/deleteUser")
	public boolean deleteUser()
	{
		return true;
	}
}
