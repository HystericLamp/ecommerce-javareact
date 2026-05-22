package com.ecommerce.bcruz.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.bcruz.dto.AuthResponse;
import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;
import com.ecommerce.bcruz.service.AuthService;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AuthServiceTest
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private AuthService authService;
	
	@BeforeEach
	void setUp()
	{
		// password123
		User testUser = new User("testMember", "member@shop.com", "$2a$10$UZlIhhQyPmHU9p9MIrkLeeK..3vgjGNg/oLOojzkknWdzu9AISwum");
		userRepository.save(testUser);
		authService = new AuthService(userRepository, passwordEncoder);
	}
	
	@AfterEach
	void tearDown()
	{
		userRepository.deleteByEmail("member@shop.com");
	}
	
	@Test
	@DisplayName("AC-USER-02: Login as an Existing User")
	void testLogin() 
	{
	    AuthResponse response = authService.login("member@shop.com", "password123");

	    assertNotNull(response);
	    assertEquals("member@shop.com", response.getEmail());
	}
	
	@Test
	void testLoginInvalidPassword()
	{
	    assertThrows(RuntimeException.class, () -> {
	        authService.login("member@shop.com", "wrongpassword");
	    });
	}
}
