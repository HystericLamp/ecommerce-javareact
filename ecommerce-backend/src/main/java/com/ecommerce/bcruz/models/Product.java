package com.ecommerce.bcruz.models;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stock;
	
	@ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	public Product() {}
	
	public Product(String name, BigDecimal price)
	{
		this.name = name;
		this.price = price;
	}
	
	public Product(String name, String description, BigDecimal price, Integer stock, Category category)
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}
	
	// Getters & Setters
	public Long getId() {return this.id; }
	public String getName(){ return name; }
	public void setName(String name){ this.name = name; }
	public String getDescription(){ return description; }
	public void setDescription(String description) { this.description = description; }
	public BigDecimal getPrice(){ return price; }
	public void setPrice(BigDecimal price) { this.price = price; }
	public Integer getStock() { return stock; }
	public void setStock(Integer stock) { this.stock = stock; }
	public Category getCategory() { return category; }
	public void setCategory(Category category) { this.category = category; }
	
	@Override
	public String toString() 
	{
	    return "Product{name='" + name + "', price=" + price + "}";
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		
		Product product =(Product) o;
		
		return this.price.compareTo(product.price) == 0 &&
			   this.name.equalsIgnoreCase(product.name) &&
			   this.id == product.id;
	}
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(name.toLowerCase(), price);
	}
}
