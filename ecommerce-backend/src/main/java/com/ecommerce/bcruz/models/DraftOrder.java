package com.ecommerce.bcruz.models;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DraftOrder
{
	private final Map<Product, LineProduct> products = new LinkedHashMap<Product, LineProduct>();
	
	public DraftOrder(Cart cart)
	{
		for (LineProduct lineProduct : cart.getAllItems())
		{
			products.put(lineProduct.getProduct(), 
					  new LineProduct(lineProduct.getProduct(), lineProduct.getQuantity()));
		}
	}
	
	public LineProduct getLineItem(Product product)
	{
		return products.get(product);
	}
	
	public final Collection<LineProduct> getAllLineItems()
	{
		return this.products.values();
	}
	
	public void updateQuantity(Product product, int quantity) 
	{
        LineProduct lineItem = products.get(product);
        
        if (lineItem == null) throw new IllegalArgumentException();
        
        lineItem.setQuantity(quantity);
    }

    public void removeItem(Product product) 
    {
        products.remove(product);
    }

    public Order finalizeOrder() 
    {
        return new Order(products.values());
    }
}
