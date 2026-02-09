package ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DraftOrder
{
	private final Map<Item, LineItem> items = new LinkedHashMap<Item, LineItem>();
	
	public DraftOrder(Cart cart)
	{
		for (LineItem lineItem : cart.getAllItems())
		{
			items.put(lineItem.getItem(), 
					  new LineItem(lineItem.getItem(), lineItem.getQuantity()));
		}
	}
	
	public LineItem getLineItem(Item item)
	{
		return items.get(item);
		
	}
	
	public void updateQuantity(Item item, int quantity) 
	{
        LineItem lineItem = items.get(item);
        
        if (lineItem == null) throw new IllegalArgumentException();
        
        lineItem.setQuantity(quantity);
    }

    public void removeItem(Item item) 
    {
        items.remove(item);
    }
    
    public List<BigDecimal> getItemTotals()
    {
    	List<LineItem> lineItems = new ArrayList<LineItem>(items.values());
    	
    	List<BigDecimal> lineItemAmounts = new ArrayList<BigDecimal>();
    	for (LineItem lineItem : lineItems)
    	{
    		lineItemAmounts.add(lineItem.getItemTotal());
    	}
    	
    	return lineItemAmounts;
    }

    public BigDecimal getTotal() 
    {
        return items.values().stream()
            .map(LineItem::getItemTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.HALF_UP);
    }

    public Order finalizeOrder() 
    {
        return new Order(items.values());
    }
}
