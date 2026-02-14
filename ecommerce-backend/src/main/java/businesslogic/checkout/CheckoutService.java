package businesslogic.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.bcruz.infrastructure.payment.PaymentProcessor;

import ecommerce.exceptions.QuantityZeroOrNegativeException;
import ecommerce.model.DraftOrder;
import ecommerce.model.Item;
import ecommerce.model.LineItem;

public class CheckoutService
{	
	public CheckoutService() {}
	
	public List<BigDecimal> getItemTotals(DraftOrder draftOrder)
    {
    	List<LineItem> lineItems = new ArrayList<LineItem>(draftOrder.getAllLineItems());
    	
    	List<BigDecimal> lineItemAmounts = new ArrayList<BigDecimal>();
    	for (LineItem lineItem : lineItems)
    	{
    		lineItemAmounts.add(lineItem.getItemTotal());
    	}
    	
    	return lineItemAmounts;
    }

    public BigDecimal getTotal(DraftOrder draftOrder) 
    {
        return draftOrder.getAllLineItems().stream()
            .map(LineItem::getItemTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.HALF_UP);
    }
	
	public DraftOrder updateOrderItemQuantity(DraftOrder draftOrder, Item item, int quantity) throws QuantityZeroOrNegativeException
	{
		if (quantity <= 0)
		{
			throw new QuantityZeroOrNegativeException("Tried to set quantity of " 
													 + item.getName() + " to or below 0");
		}
		
		draftOrder.updateQuantity(item, quantity);
		return draftOrder;
	}
	
	/**
	 * <p>
	 * Removes an <b>Item</b> from the <b>Order</b>.
	 * If <b>Item</b> does not exist in <b>Order</b> then nothing happens.
	 * </p>
	 * 
	 * @param draftOrder
	 * @param item
	 * @return
	 */
	public DraftOrder removeItemFromOrder(DraftOrder draftOrder, Item item)
	{
		draftOrder.removeItem(item);
		return draftOrder;
	}
	
	public boolean makePayment(DraftOrder draftOrder, String currencyType, PaymentProcessor paymentProcessor)
	{
		long amount = getTotal(draftOrder).longValue();
		
		String paymentID = paymentProcessor.createPayment(amount, currencyType);
		boolean paymentResult = paymentProcessor.confirmPayment(paymentID);
		return paymentResult;
	}
	
	public boolean refundPayment(String paymentID, PaymentProcessor paymentProcessor)
	{
		return paymentProcessor.refundPayment(paymentID);
	}
}
