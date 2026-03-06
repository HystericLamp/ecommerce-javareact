package com.ecommerce.bcruz.controller.tests;

import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.ecommerce.bcruz.controller.ProductController;
import com.ecommerce.bcruz.models.Category;
import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.service.ProductService;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private ProductService productService;
	
	// Helper
	private Product product(String name, String desc, String price, Integer stock, String category)
	{
		return new Product(name, desc, new BigDecimal(price), stock, new Category(category));
	}
	
	@Test
	@DisplayName("AC-PRODUCT-MENU-01: Get All Product Information (Controller)")
	void menu_getAllProductsApi() throws Exception
	{
		List<Product> products = new ArrayList<Product>();
		products.add(product("name1", "desc1", "1000", 1, "category1"));
		products.add(product("name2", "desc2", "2000", 2, "category2"));
		products.add(product("name3", "desc3", "3000", 3, "category3"));
		products.add(product("name4", "desc4", "4000", 4, "category4"));
		products.add(product("name5", "desc5", "5000", 5, "category5"));

        Mockito.when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/getAllProducts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(5))
                .andExpect(jsonPath("$[0].name").value("name1"))
                .andExpect(jsonPath("$[4].name").value("name5"));

        Mockito.verify(productService).getAllProducts();
	}
}
