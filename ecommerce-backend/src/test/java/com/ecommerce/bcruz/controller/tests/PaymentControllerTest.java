package com.ecommerce.bcruz.controller.tests;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.bcruz.controller.PaymentController;
import com.ecommerce.bcruz.infrastructure.payment.PaymentProcessor;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc
public class PaymentControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PaymentProcessor paymentProcessor;
	
	@Test
	void shouldCreatePaymentIntent() throws Exception
	{
		when(paymentProcessor.createPayment(5000L, "usd"))
			.thenReturn("test_client_secret");
		
		mockMvc.perform(post("/api/payments/create/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.clientSecret")
				.value("test_client_secret"));
	}
}
