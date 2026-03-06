package com.ecommerce.bcruz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.repositories.ProductRepository;

@Service
public class ProductService
{
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}
	
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
}
