package com.ecommerce.bcruz.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.models.DraftOrderItem;
import com.ecommerce.bcruz.models.DraftOrderStatus;
import com.ecommerce.bcruz.repositories.DraftOrderRepository;
import com.ecommerce.bcruz.repositories.OrderRepository;
import com.ecommerce.bcruz.service.WebhookService;
import com.stripe.model.PaymentIntent;

@ExtendWith(MockitoExtension.class)
public class WebhookServiceTest
{
	@Mock
	private DraftOrderRepository draftOrderRepository;
	
	@Mock
	private OrderRepository orderRepository;
	
	@InjectMocks
	private WebhookService webhookService;
	
	@Test
	void shouldConvertDraftOrderToOrderOnPaymentSuccess() throws Exception 
	{
	    PaymentIntent intent = new PaymentIntent();

	    Map<String, String> metadata = new HashMap<>();
	    metadata.put("draftOrderId", "1");

	    intent.setMetadata(metadata);

	    DraftOrder draft = new DraftOrder();
	    draft.setId(1L);
	    draft.setStatus(DraftOrderStatus.PENDING);
	    draft.setTotalAmountInCents(1000L);

	    // IMPORTANT: items must not be null
	    draft.setItems(List.of(new DraftOrderItem()));

	    when(draftOrderRepository.findById(1L))
	            .thenReturn(Optional.of(draft));
	    
	    webhookService.handleSuccessfulPayment(intent);

	    verify(orderRepository).save(any());
	    verify(draftOrderRepository).save(draft);

	    assertEquals(DraftOrderStatus.COMPLETED, draft.getStatus());
	}
}
