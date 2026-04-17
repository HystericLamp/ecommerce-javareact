package com.ecommerce.bcruz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.exceptions.DuplicateDraftOrderException;
import com.ecommerce.bcruz.service.WebhookService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController
{
	private final WebhookService webhookService;
	
	@Value("${stripe.webhook.secret}")
	private String endpointSecret;
	
	public WebhookController(WebhookService webhookService)
	{
		this.webhookService = webhookService;
	}
	
	@PostMapping
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload,
                                                      @RequestHeader("Stripe-Signature") String sigHeader) throws SignatureVerificationException 
	{
        Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);

        if ("payment_intent.succeeded".equals(event.getType())) 
        {
            PaymentIntent intent = (PaymentIntent) event.getDataObjectDeserializer()
                    .getObject()
                    .orElse(null);
            try
			{
				webhookService.handleSuccessfulPayment(intent);
			} 
            catch (DuplicateDraftOrderException e)
			{
				e.printStackTrace();
			}
        }

        return ResponseEntity.ok("");
    }
}
