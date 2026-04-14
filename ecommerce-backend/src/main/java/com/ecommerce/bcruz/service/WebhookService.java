package com.ecommerce.bcruz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.exceptions.DuplicateDraftOrderException;
import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.models.DraftOrderStatus;
import com.ecommerce.bcruz.models.Order;
import com.ecommerce.bcruz.models.OrderItem;
import com.ecommerce.bcruz.models.OrderStatus;
import com.ecommerce.bcruz.repositories.DraftOrderRepository;
import com.ecommerce.bcruz.repositories.OrderRepository;
import com.stripe.model.PaymentIntent;

@Service
public class WebhookService
{
	private DraftOrderRepository draftOrderRepository;
	private OrderRepository orderRepository;
	
	public DraftOrder handleSuccessfulPayment(PaymentIntent intent) throws DuplicateDraftOrderException 
	{
	    String draftOrderId = intent.getMetadata().get("draftOrderId");
	    
	    System.out.println("draftOrderRepo finding");
	    DraftOrder draftOrder = draftOrderRepository.findById(Long.valueOf(draftOrderId))
	            .orElseThrow();

	    // Prevent duplicate processing
	    if (draftOrder.getStatus() == DraftOrderStatus.COMPLETED) {
	    	System.out.println("Throwing");
	        throw new DuplicateDraftOrderException("Duplicate Draft Order");
	    }

	    Order order = convertToOrder(draftOrder);
	    
	    orderRepository.save(order);
	    
	    draftOrder.setStatus(DraftOrderStatus.COMPLETED);
	    return draftOrderRepository.save(draftOrder);
	}
	
	public Order convertToOrder(DraftOrder draft) 
	{
	    Order order = new Order();
	    order.setUserId(draft.getUserId());
	    order.setTotalAmountInCents(draft.getTotalAmountInCents());
	    order.setStatus(OrderStatus.PAID);
	    order.setPaymentIntentId(draft.getPaymentIntentId());

	    List<OrderItem> items = draft.getItems().stream().map(d -> {
	        OrderItem i = new OrderItem();
	        i.setProductId(d.getProductId());
	        i.setQuantity(d.getQuantity());
	        i.setPriceAtPurchase(d.getPriceAtCheckout());
	        i.setOrder(order);
	        return i;
	    }).toList();

	    order.setItems(items);

	    return order;
	}
}
