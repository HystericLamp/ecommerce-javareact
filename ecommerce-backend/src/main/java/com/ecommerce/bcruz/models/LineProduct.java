package com.ecommerce.bcruz.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LineProduct
{
	private Product product;
	private int quantity;
	
	public LineProduct(Product product, int quantity)
	{
		this.setProduct(product);
		this.setQuantity(quantity);
	}
	
	// Setters & Getters
	public Product getProduct() { return product; }
	public void setProduct(Product product) { this.product = product; }
	public int getQuantity() { return quantity; }
	
	public void setQuantity(int quantity) 
	{
		if (quantity <= 0) 
		{
			throw new IllegalArgumentException("Cannot set quantity of " + product.getName() + " to or below 0");
		}
		
		this.quantity = quantity; 
	}
	
	/**
	 * <p>
	 * Calculates the total cost of the <b>Product</b>'s price multiplied by its quantity
	 * </p>
	 * 
	 * @return
	 */
	public BigDecimal getItemTotal() 
	{
		return product.getPrice().multiply(BigDecimal.valueOf(quantity));
	}
}
