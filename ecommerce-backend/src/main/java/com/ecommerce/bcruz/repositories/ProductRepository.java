package com.ecommerce.bcruz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bcruz.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{
	List<Product> findByCategoryId(Long categoryId);
	List<Product> findByNameContainingIgnoreCase(String name);
	
	// Extending JpaRepository provides the below methods
	// .findAll();
	// .findById(1L);
	// .save(product);
	// .delete(product);
	// .count();
}
