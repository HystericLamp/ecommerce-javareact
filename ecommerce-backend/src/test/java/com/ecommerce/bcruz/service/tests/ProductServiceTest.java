package com.ecommerce.bcruz.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.bcruz.models.Category;
import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.repositories.ProductRepository;
import com.ecommerce.bcruz.service.ProductService;

public class ProductServiceTest
{
	private ProductService productService;
	private ProductRepository productRepository;
	
	// Helper
	private Product product(String name, String desc, String price, Integer stock, String category)
	{
		return new Product(name, desc, new BigDecimal(price), stock, new Category(category));
	}
	
	@BeforeEach
	void setUp()
	{
		productRepository = mock(ProductRepository.class);
		productService = new ProductService(productRepository);
	}
	
	@Test
	@DisplayName("AC-PRODUCT-MENU-01: Get All Product Information (Service)")
	void menu_getAllProducts()
	{
		List<Product> productRepoResult = new ArrayList<Product>();
		productRepoResult.add(product("name1", "desc1", "1000", 1, "category1"));
		productRepoResult.add(product("name2", "desc2", "2000", 2, "category2"));
		productRepoResult.add(product("name3", "desc3", "3000", 3, "category3"));
		productRepoResult.add(product("name4", "desc4", "4000", 4, "category4"));
		productRepoResult.add(product("name5", "desc5", "5000", 5, "category5"));
		
		when(productRepository.findAll()).thenReturn(productRepoResult);
		
		List<Product> result = productService.getAllProducts();
		assert(result != null);
		assertEquals(5, result.size());
		assertEquals("name1", result.get(0).getName());
		assertEquals("name5", result.get(4).getName());
	}
}
