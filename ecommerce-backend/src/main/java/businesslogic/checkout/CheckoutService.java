package businesslogic.checkout;


import ecommerce.model.DraftOrder;

public class CheckoutService
{
	private DraftOrder draftOrder;
	
	public CheckoutService(DraftOrder draftOrder) 
	{
		this.draftOrder = draftOrder;
	}
	
	public DraftOrder getOrder()
	{
		return this.draftOrder;
	}
	
	public void updateOrderItemQuantity(String itemName, int quantity)
	{
		
	}
}
