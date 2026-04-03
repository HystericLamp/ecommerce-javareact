package com.ecommerce.bcruz.models;

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

	public int getOrderID(){ return orderID; }
	public void setOrderID(int orderID){ this.orderID = orderID; }
}
