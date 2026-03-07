package com.ecommerce.bcruz.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bcruz.models.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	List<User> findByNameContainingIgnoreCase(String name);
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
	
	// Extending JpaRepository provides the below methods
	// .findAll();
	// .findById(1L);
	// .save(product);
	// .delete(product);
	// .count();
}
