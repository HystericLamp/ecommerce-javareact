package com.ecommerce.bcruz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bcruz.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
	Category findByName(String name);
}
