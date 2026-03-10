package com.ecommerce.bcruz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.dto.AuthResponse;
import com.ecommerce.bcruz.dto.LoginRequest;
import com.ecommerce.bcruz.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
	private final AuthService authService;
	
	public AuthController(AuthService authService)
	{
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
	{
		AuthResponse response = authService.login(
			request.getEmail(), 
			request.getPassword()
		);
		
		return ResponseEntity.ok(response);
	}
}
