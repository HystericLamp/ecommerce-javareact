package com.ecommerce.bcruz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.dto.CartItem;
import com.ecommerce.bcruz.dto.CheckoutRequest;
import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.models.LineProduct;
import com.ecommerce.bcruz.models.Order;
import com.ecommerce.bcruz.models.OrderItem;
import com.ecommerce.bcruz.models.OrderStatus;
import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.repositories.DraftOrderRepository;
import com.ecommerce.bcruz.repositories.OrderRepository;
import com.ecommerce.bcruz.repositories.ProductRepository;

@Service
public class CheckoutService
{
	private final ProductRepository productRepository;
	private final DraftOrderRepository draftOrderRepository;
	private final OrderRepository orderRepository;
	
	public CheckoutService(ProductRepository productRepository, 
						   DraftOrderRepository draftOrderRepository, 
						   OrderRepository orderRepository)
	{
		this.productRepository = productRepository;
		this.draftOrderRepository = draftOrderRepository;
		this.orderRepository = orderRepository;
	}
	
	public Order createDraftOrder(CheckoutRequest request, Long userId)
	{
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		long total = 0L;
		
		for (CartItem item : request.getItemProducts())
		{
			Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
			
			long price = product.getPrice().longValue();
			
			OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setPriceAtPurchase(price);
            orderItem.setQuantity(item.getQuantity());

            orderItems.add(orderItem);

            total += price * item.getQuantity();
		}
		
		Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmountInCents(total);
        order.setCurrency("usd");
        order.setStatus(OrderStatus.PENDING);

        order.setItems(orderItems);
        orderItems.forEach(i -> i.setOrder(order));

        return orderRepository.save(order);
	}
}
