package com.ecommerce.bcruz.service.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;
import com.ecommerce.bcruz.service.UserService;

@SpringBootTest
public class UserServiceTest
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private UserService userService;
	
	@BeforeEach
	void setUp()
	{
		userService = new UserService(userRepository, passwordEncoder);
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
	    boolean result = userService.login("member1@shop.com", "password1");
	    assertTrue(result);
	}
}
