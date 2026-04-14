package com.ecommerce.bcruz.controller.tests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.ecommerce.bcruz.controller.WebhookController;
import com.ecommerce.bcruz.service.WebhookService;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;

@WebMvcTest(WebhookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WebhookControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@MockitoBean
	private WebhookService webhookService;
	
	@Test
	void shouldHandleWebhookAPICall() throws Exception
	{
		String payload = "{ \"type\": \"payment_intent.succeeded\" }";
        String signature = "test-signature";
        
        // Mock PaymentIntent
        PaymentIntent intent = new PaymentIntent();
        Map<String, String> metadata = new HashMap<>();
        metadata.put("draftOrderId", "1");
        intent.setMetadata(metadata);
        
        // Mock Event
        Event event = mock(Event.class);
        EventDataObjectDeserializer deserializer = mock(EventDataObjectDeserializer.class);
        
        when(event.getType()).thenReturn("payment_intent.succeeded");
        when(event.getDataObjectDeserializer()).thenReturn(deserializer);
        when(deserializer.getObject()).thenReturn(Optional.of(intent));
        
        // Mock static Stripe method
        try (MockedStatic<Webhook> mockedWebhook = Mockito.mockStatic(Webhook.class)) {

            mockedWebhook.when(() ->
                    Webhook.constructEvent(anyString(), anyString(), anyString())
            ).thenReturn(event);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/webhook")
                    .content(payload)
                    .header("Stripe-Signature", signature)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(webhookService).handleSuccessfulPayment(intent);
        }
	}
}
