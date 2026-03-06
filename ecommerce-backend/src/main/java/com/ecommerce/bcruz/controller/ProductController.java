package com.ecommerce.bcruz.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
	private final ProductService productService;
	
	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts()
	{
		return productService.getAllProducts();
	}
}
