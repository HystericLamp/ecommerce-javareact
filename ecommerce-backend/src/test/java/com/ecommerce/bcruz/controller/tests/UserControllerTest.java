package com.ecommerce.bcruz.controller.tests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ecommerce.bcruz.controller.UserController;
import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private UserService userService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	void user_getUserByIdEndpoint() throws Exception
	{
		User user = new User("Abbigail", "test@email.com", "testPassw0rd");
		user.setId(1L);
		
		Mockito.when(userService.getUserById(1L))
				.thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/by-id/{id}", user.getId())
		        .param("email", "test@email.com"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value(1L))
		        .andExpect(jsonPath("$.name").value("Abbigail"));
	}
	
	@Test
	void user_getUserByEmailEndpoint() throws Exception
	{
		User user = new User("Abbigail", "test@email.com", "testPassw0rd");
		user.setId(1L);
		
		Mockito.when(userService.getUserByEmail("test@email.com"))
				.thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/by-email")
		        .param("email", "test@email.com"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.email").value("test@email.com"))
		        .andExpect(jsonPath("$.name").value("Abbigail"));
	}
	
	@Test
	void user_registerNewuser() throws Exception
	{
		User user = new User("Abbigail", "test@email.com", "testPassw0rd");
		user.setId(1L);
		
		when(userService.createUser(any(User.class))).thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/users/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Abbigail"))
                .andExpect(jsonPath("$.email").value("test@email.com"));
	}
	
	@Test
	void user_updateUser() throws Exception
	{
		User existingUser = new User("Peter", "tesrs@email.com", "testPassword");
	    existingUser.setId(1L);

	    User updatedUser = new User("Abbigail", "test@email.com", "testPassw0rd");
	    updatedUser.setId(1L);

	    when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);
	    
	    mockMvc.perform(MockMvcRequestBuilders.put("/api/users/updateUser/{id}", updatedUser.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(existingUser)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id").value(1L))
	            .andExpect(jsonPath("$.name").value("Abbigail"))
	            .andExpect(jsonPath("$.email").value("test@email.com"));
	}
	
	@Test
	void user_deleteUser() throws Exception
	{
		Long userId = 1L;
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/deleteUser/{id}", userId))
				.andExpect(status().isOk());
		
		Mockito.verify(userService).deleteUserById(userId);
	}
}
