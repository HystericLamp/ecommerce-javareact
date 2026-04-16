package com.ecommerce.bcruz.dto;

import java.util.List;

public class CheckoutRequest
{
	private Long userId;
	private List<CartItem> itemProducts;
	
	
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
	public List<CartItem> getItemProducts() { return itemProducts; }
	public void setItemProducts(List<CartItem> itemProducts) { this.itemProducts = itemProducts; }
}
