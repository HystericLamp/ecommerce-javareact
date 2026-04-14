package com.ecommerce.bcruz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.exceptions.DuplicateDraftOrderException;
import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.models.DraftOrderStatus;
import com.ecommerce.bcruz.models.Order;
import com.ecommerce.bcruz.models.OrderItem;
import com.ecommerce.bcruz.models.OrderStatus;
import com.ecommerce.bcruz.repositories.DraftOrderRepository;
import com.ecommerce.bcruz.repositories.OrderRepository;
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
	
	@Value("${STRIPE_WEBHOOK_SECRET}")
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

        if ("payment_intent.succeeded".equals(event.getType())) {

            PaymentIntent intent = (PaymentIntent) event.getDataObjectDeserializer()
                    .getObject()
                    .orElse(null);

            try
			{
            	System.out.println("About to go to handleSuccessfulPayment()");
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
