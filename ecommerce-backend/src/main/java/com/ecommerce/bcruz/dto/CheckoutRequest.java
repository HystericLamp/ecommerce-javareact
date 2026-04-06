package com.ecommerce.bcruz.dto;

import java.util.List;

public class CheckoutRequest
{
	private List<CartItem> itemProducts;

	public List<CartItem> getItemProducts() { return itemProducts; }
	public void setItemProducts(List<CartItem> itemProducts) { this.itemProducts = itemProducts; }
}
