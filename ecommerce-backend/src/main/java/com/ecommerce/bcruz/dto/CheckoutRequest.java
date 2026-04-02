package com.ecommerce.bcruz.dto;

import java.util.List;

public class CheckoutRequest
{
	private List<CartItem> itemProducts;

	public List<CartItem> getItemProducts() { return itemProducts; }
	public void setProducts(List<CartItem> itemProducts) { this.itemProducts = itemProducts; }
}
