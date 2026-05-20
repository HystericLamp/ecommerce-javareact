package com.ecommerce.bcruz.controller.tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ecommerce.bcruz.controller.AuthController;
import com.ecommerce.bcruz.dto.AuthResponse;
import com.ecommerce.bcruz.dto.LoginRequest;
import com.ecommerce.bcruz.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockitoBean
	private AuthService authService;
	
	@Test
	@DisplayName("AC-USER-02: Login as an Existing User")
    void login_shouldReturnAuthResponse() throws Exception
    {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@email.com");
        request.setPassword("password123");

        AuthResponse response = new AuthResponse(1L, "test@email.com");

        when(authService.login(
            "test@email.com",
            "password123"
        )).thenReturn(response);

        mockMvc.perform(
        		MockMvcRequestBuilders.post("/api/auth/login")
        			.with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.userId").value(1))
            .andExpect(jsonPath("$.email").value("test@email.com"));
    }
}