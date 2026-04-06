package com.ecommerce.bcruz.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.bcruz.dto.CartItem;
import com.ecommerce.bcruz.dto.CheckoutRequest;
import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.models.DraftOrderStatus;
import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.repositories.DraftOrderRepository;
import com.ecommerce.bcruz.repositories.ProductRepository;
import com.ecommerce.bcruz.service.CheckoutService;

public class CheckoutServiceTest
{
	private ProductRepository productRepository;
	private DraftOrderRepository draftOrderRepository;
	private CheckoutService checkoutService;
	
	// Helper
	private Product createProduct(Long id, String name, long price) {
	    Product p = new Product();
	    p.setName(name);
	    p.setPrice(price);

	    try {
	        Field idField = Product.class.getDeclaredField("id");
	        idField.setAccessible(true);
	        idField.set(p, id);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }

	    return p;
	}
	
	@BeforeEach
	void setUp()
	{
		productRepository = mock(ProductRepository.class);
		draftOrderRepository = mock(DraftOrderRepository.class);
		checkoutService = new CheckoutService(productRepository, draftOrderRepository);
	}
	
	@Test
    @DisplayName("AC-CHECKOUT-01: Get Summary of Order as guest")
	void checkout_getOrderGuest()
	{
		// Test Data
		List<CartItem> itemProducts = new ArrayList<CartItem>();
		itemProducts.add(new CartItem(1L, 1));
		itemProducts.add(new CartItem(2L, 2));
		itemProducts.add(new CartItem(3L, 3));
		itemProducts.add(new CartItem(4L, 4));
		
		CheckoutRequest request = new CheckoutRequest();
		request.setItemProducts(itemProducts);
		
		// Mock Products
		when(productRepository.findById(1L))
        .thenReturn(Optional.of(createProduct(1L, "P1", 100)));

		when(productRepository.findById(2L))
		        .thenReturn(Optional.of(createProduct(2L, "P2", 200)));
		
		when(productRepository.findById(3L))
		        .thenReturn(Optional.of(createProduct(3L, "P3", 300)));
		
		when(productRepository.findById(4L))
		        .thenReturn(Optional.of(createProduct(4L, "P4", 400)));

	    // Mock save
	    when(draftOrderRepository.save(any(DraftOrder.class)))
	            .thenAnswer(invocation -> invocation.getArgument(0));

	    // Execute
	    DraftOrder result = checkoutService.createDraftOrder(request);

	    // Assert
	    assertNotNull(result);
	    assertEquals(4, result.getItems().size());

	    // total = 100*1 + 200*2 + 300*3 + 400*4 = 3000
	    assertEquals(3000, result.getTotalAmountInCents());
	    assertEquals("usd", result.getCurrency());
	    assertEquals(DraftOrderStatus.PENDING, result.getStatus());
	}
	
	@Test
    @DisplayName("AC-CHECKOUT-01: Get Summary of Order as a User")
	void checkout_getOrderUser()
	{
		// Test Data
		List<CartItem> itemProducts = new ArrayList<CartItem>();
		itemProducts.add(new CartItem(1L, 1));
		itemProducts.add(new CartItem(2L, 2));
		itemProducts.add(new CartItem(3L, 3));
		itemProducts.add(new CartItem(4L, 4));
		
		CheckoutRequest request = new CheckoutRequest();
		request.setItemProducts(itemProducts);
		
		// Mock Products
		when(productRepository.findById(1L))
        .thenReturn(Optional.of(createProduct(1L, "P1", 100)));

		when(productRepository.findById(2L))
		        .thenReturn(Optional.of(createProduct(2L, "P2", 200)));
		
		when(productRepository.findById(3L))
		        .thenReturn(Optional.of(createProduct(3L, "P3", 300)));
		
		when(productRepository.findById(4L))
		        .thenReturn(Optional.of(createProduct(4L, "P4", 400)));

	    // Mock save
	    when(draftOrderRepository.save(any(DraftOrder.class)))
	            .thenAnswer(invocation -> invocation.getArgument(0));

	    // Execute
	    DraftOrder result = checkoutService.createDraftOrder(request, 22L);

	    // Assert
	    assertNotNull(result);
	    assertEquals(4, result.getItems().size());

	    // total = 100*1 + 200*2 + 300*3 + 400*4 = 3000
	    assertEquals(3000, result.getTotalAmountInCents());
	    assertEquals("usd", result.getCurrency());
	    assertEquals(DraftOrderStatus.PENDING, result.getStatus());
	}
}
