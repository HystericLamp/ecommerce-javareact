package com.ecommerce.bcruz.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderDEPRECATED
{
	private int orderID;
	private final List<LineProduct> products;
	
	public OrderDEPRECATED(Collection<LineProduct> cartItems)
	{
		this.setOrderID(orderID);
		this.products = cartItems.stream()
					 .map(li -> new LineProduct(li.getProduct(), li.getQuantity()))
					 .toList();
	}
	
	public OrderDEPRECATED(int orderID, Collection<LineProduct> cartItems)
	{
		this.setOrderID(orderID);
		this.products = cartItems.stream()
					 .map(li -> new LineProduct(li.getProduct(), li.getQuantity()))
					 .toList();
	}
	
	public List<LineProduct> getCartItems() { return this.products; }
	
	public BigDecimal getItemTotal(Product product)
	{
		for (LineProduct lineProduct : products)
		{
			if (lineProduct.getProduct().equals(product))
			{
				return lineProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(lineProduct.getQuantity()));
			}
		}
		
		return null;
	}
	
	public BigDecimal getCartSum()
	{
		return products.stream()
	            .map(LineProduct::getItemTotal)
	            .reduce(BigDecimal.ZERO, BigDecimal::add)
	            .setScale(2, RoundingMode.HALF_UP);
	}

	public int getOrderID(){ return orderID; }
	public void setOrderID(int orderID){ this.orderID = orderID; }
}
