package com.ecommerce.bcruz.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Product
{
	private String name;
	private String description;
	private BigDecimal price;
	
	public Product(String name, BigDecimal price)
	{
		this.name = name;
		this.price = price;
	}
	
	public Product(String name, String description, BigDecimal price)
	{
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	// Getters & Setters
	public String getName(){ return name; }
	public void setName(String name){ this.name = name; }
	public String getDescription(){ return description; }
	public void setDescription(String description) { this.description = description; }
	public BigDecimal getPrice(){ return price; }
	public void setPrice(BigDecimal price) { this.price = price; }
	
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
		
		Product item =(Product) o;
		
		return price.compareTo(item.price) == 0 &&
			   name.equalsIgnoreCase(item.name);
	}
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(name.toLowerCase(), price);
	}
}
