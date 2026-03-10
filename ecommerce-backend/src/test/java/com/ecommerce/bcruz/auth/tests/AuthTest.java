package com.ecommerce.bcruz.auth.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.bcruz.dto.AuthResponse;
import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;
import com.ecommerce.bcruz.service.AuthService;

@SpringBootTest
public class AuthTest
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private AuthService authService;
	
	@BeforeEach
	void setUp()
	{
		authService = new AuthService(userRepository, passwordEncoder);
	}
	
	@Test
	void testPasswordMatchRawValues()
	{
		String rawPasswordMember1 = "password1";
        String rawPasswordMember2 = "password2";
        
        Optional<User> member1Opt = userRepository.findByEmail("member1@shop.com");
        Optional<User> member2Opt = userRepository.findByEmail("member2@shop.com");

        assertTrue(member1Opt.isPresent(), "Member1 should exist");
        assertTrue(member2Opt.isPresent(), "Member2 should exist");

        User member1 = member1Opt.get();
        User member2 = member2Opt.get();
        
        assertTrue(passwordEncoder.matches(rawPasswordMember1, member1.getPassword()), "Member1 password should match");
        assertTrue(passwordEncoder.matches(rawPasswordMember2, member2.getPassword()), "Member2 password should match");
	}
	
	@Test
	void testLogin() 
	{
	    AuthResponse response = authService.login("member1@shop.com", "password1");

	    assertNotNull(response);
	    assertEquals("member1@shop.com", response.getEmail());
	}
	
	@Test
	void testLoginInvalidPassword()
	{
	    assertThrows(RuntimeException.class, () -> {
	        authService.login("member1@shop.com", "wrongpassword");
	    });
	}
}
