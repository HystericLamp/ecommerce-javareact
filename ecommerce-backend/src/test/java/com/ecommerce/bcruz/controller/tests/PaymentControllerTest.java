package com.ecommerce.bcruz.controller.tests;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.bcruz.controller.PaymentController;
import com.ecommerce.bcruz.infrastructure.payment.PaymentProcessor;
import com.stripe.model.PaymentIntent;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PaymentControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PaymentProcessor paymentProcessor;
	
	@Test
	void shouldCreatePaymentIntent() throws Exception
	{
        PaymentIntent mockIntent = Mockito.mock(PaymentIntent.class);
        
        when(paymentProcessor.createPayment(anyLong(), anyString(), anyMap()))
        .thenReturn(mockIntent);
        
        mockMvc.perform(post("/payments")
                .param("amount", "1000")
                .param("currency", "usd"))
                .andExpect(status().isOk());
		
        verify(paymentProcessor).createPayment(
                eq(1000L),
                eq("usd"),
                anyMap()
        );
	}
}
