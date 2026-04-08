package com.ecommerce.bcruz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.dto.CartItem;
import com.ecommerce.bcruz.dto.CheckoutRequest;
import com.ecommerce.bcruz.dto.PaymentResult;
import com.ecommerce.bcruz.infrastructure.payment.PaymentProcessor;
import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.models.DraftOrderItem;
import com.ecommerce.bcruz.models.DraftOrderStatus;
import com.ecommerce.bcruz.models.LineProduct;
import com.ecommerce.bcruz.models.Order;
import com.ecommerce.bcruz.models.OrderItem;
import com.ecommerce.bcruz.models.OrderStatus;
import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.repositories.DraftOrderRepository;
import com.ecommerce.bcruz.repositories.OrderRepository;
import com.ecommerce.bcruz.repositories.ProductRepository;
import com.stripe.model.PaymentIntent;

@Service
public class CheckoutService
{
	private final ProductRepository productRepository;
	private final DraftOrderRepository draftOrderRepository;
	private final PaymentProcessor paymentProcessor;
	
	public CheckoutService(ProductRepository productRepository, 
						   DraftOrderRepository draftOrderRepository,
						   PaymentProcessor paymentProcessor)
	{
		this.productRepository = productRepository;
		this.draftOrderRepository = draftOrderRepository;
		this.paymentProcessor = paymentProcessor;
	}
	
	/**
	 * Create Draft Order
	 * @param request
	 * @param userId
	 * @return
	 */
	public DraftOrder createDraftOrder(CheckoutRequest request, Long userId)
	{
	    List<DraftOrderItem> items = new ArrayList<>();
	    long total = 0;

	    for (CartItem cartItem : request.getItemProducts()) {

	        Product product = productRepository.findById(cartItem.getProductId())
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        long price = product.getPrice();

	        DraftOrderItem item = new DraftOrderItem();
	        item.setProductId(product.getId());
	        item.setProductName(product.getName());
	        item.setPriceAtCheckout(price);
	        item.setQuantity(cartItem.getQuantity());

	        items.add(item);

	        total += price * cartItem.getQuantity();
	    }

	    DraftOrder draftOrder = new DraftOrder();

	    if (userId != null) {
	        draftOrder.setUserId(userId);
	    }

	    draftOrder.setTotalAmountInCents(total);
	    draftOrder.setCurrency("usd");
	    draftOrder.setStatus(DraftOrderStatus.PENDING);

	    draftOrder.setItems(items);
	    items.forEach(i -> i.setDraftOrder(draftOrder));

	    return draftOrderRepository.save(draftOrder);
	}
	
	/**
	 * Draft Order and Payment
	 * @param request
	 * @param userId
	 * @return
	 */
	public PaymentResult createDraftOrderAndPayment(CheckoutRequest request, Long userId)
	{
	    DraftOrder draftOrder = createDraftOrder(request, userId);

	    PaymentIntent intent = paymentProcessor.createPayment(
	            draftOrder.getTotalAmountInCents(),
	            draftOrder.getCurrency(),
	            Map.of("draftOrderId", draftOrder.getId().toString())
	    );

	    draftOrder.setPaymentIntentId(intent.getId());
	    draftOrderRepository.save(draftOrder);

	    return new PaymentResult(
	            draftOrder.getId(),
	            intent.getClientSecret()
	    );
	}
}
