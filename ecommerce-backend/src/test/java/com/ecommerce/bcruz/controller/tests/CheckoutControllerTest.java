package com.ecommerce.bcruz.controller.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.ecommerce.bcruz.controller.CheckoutController;
import com.ecommerce.bcruz.dto.CheckoutRequest;
import com.ecommerce.bcruz.dto.PaymentResult;
import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.service.CheckoutService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CheckoutController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CheckoutControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private CheckoutService checkoutService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("Create a Draft Order and Save")
	void checkout_shouldCreateDraftOrder() throws Exception
	{
		CheckoutRequest request = new CheckoutRequest();
		
		DraftOrder draftOrder = new DraftOrder();
        draftOrder.setId(55L);
        draftOrder.setTotalAmountInCents(5000L);
        draftOrder.setCurrency("usd");
        
        when(checkoutService.createDraftOrder(any(), eq(1L)))
        		.thenReturn(draftOrder);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/draft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.draftOrderId").value(55L))
                .andExpect(jsonPath("$.totalAmount").value(5000L))
                .andExpect(jsonPath("$.currency").value("usd"));
	}
	
	@Test
	@DisplayName("Create a Draft Order, Save, and start Payment")
    void checkout_shouldCheckoutAndReturnClientSecret() throws Exception 
	{
        CheckoutRequest request = new CheckoutRequest();

        PaymentResult result = new PaymentResult();
        result.setDraftOrderId(200L);
        result.setClientSecret("secret_123");

        when(checkoutService.createDraftOrderAndPayment(any(), eq(1L)))
                .thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.draftOrderId").value(200L))
                .andExpect(jsonPath("$.clientSecret").value("secret_123"));
    }
}
