package com.ecommerce.bcruz.service.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;
import com.ecommerce.bcruz.service.UserService;

public class UserServiceTest
{
	private UserService userService;
	private UserRepository userRepository;
	
	@BeforeEach
	void setUp()
	{
		userRepository = mock(UserRepository.class);
		userService = new UserService(userRepository);
	}
	
	@Test
	void user_addUser()
	{
		String newName = "Peter";
		String newEmail = "Peter@email.com";
		String newPassword = "IAmPeter5";
		User newUser = new User(newName, newEmail, newPassword);
		
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
			User u = invocation.getArgument(0);
			u.setId(3L);
			return u;
		});
		
		User registeredUser = userService.createUser(newUser);
		
		assertEquals(3L, registeredUser.getId());
        assertEquals("Peter", registeredUser.getName());

        verify(userRepository, times(1)).save(newUser);
	}
	
	@Test
	void user_updateUser()
	{
		String name = "Peter";
		String email = "Peter@email.com";
		String password = "IAmPeter5";
		User user = new User(name, email, password);
		user.setId(3L);
		Optional<User> optionalUser = Optional.of(user);
		
		when(userRepository.findById(3L)).thenReturn(optionalUser);
		
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
			User u = invocation.getArgument(0);
			u.setEmail("Better@email.com");
			return u;
		});
		
		User updatedUser = userService.updateUser(3L, user);
		
		assertEquals(3L, updatedUser.getId());
        assertEquals("Peter", updatedUser.getName());
        assertEquals("Better@email.com", updatedUser.getEmail());
        
        verify(userRepository, times(1)).findById(3L);
        verify(userRepository, times(1)).save(updatedUser);
	}
	
	@Test
    void deleteUser_existingUser_returnsTrue() 
	{
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUserById(1L);

        verify(userRepository).delete(user);
    }
}
