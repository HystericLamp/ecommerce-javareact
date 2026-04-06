package com.ecommerce.bcruz.models;

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
}
