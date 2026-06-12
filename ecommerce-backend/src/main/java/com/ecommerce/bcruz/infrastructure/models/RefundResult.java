package com.ecommerce.bcruz.infrastructure.models;

import com.ecommerce.bcruz.infrastructure.enums.RefundStatus;

public class RefundResult
{
	private String id;
    private String paymentIntentId;
    private long amount;
    private RefundStatus status;
    
    public RefundResult() {}
    
    public RefundResult(String id, String paymentIntentId, long amount, RefundStatus status)
    {
    	this.id = id;
        this.paymentIntentId = paymentIntentId;
        this.amount = amount;
        this.status = status;
    }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	public String getPaymentIntentId() { return paymentIntentId; }
	public void setPaymentIntentId(String paymentIntentId) { this.paymentIntentId = paymentIntentId; }

	public long getAmount() { return amount; }
	public void setAmount(long amount) { this.amount = amount; }

	public RefundStatus getStatus() { return status; }
	public void setStatus(RefundStatus status) { this.status = status; }
}
