package com.ecommerce.bcruz.infrastructure.models;

import java.util.Map;

import com.ecommerce.bcruz.infrastructure.enums.PaymentStatus;

public class PaymentIntentResult
{
	private String id;
    private String clientSecret;
    private PaymentStatus status;
    private long amount;
    private String currency;
    private Map<String, String> metadata;
    
    public PaymentIntentResult() {}
    
	public PaymentIntentResult(String id, String clientSecret, PaymentStatus status, long amount, String currency)
	{
		this.id = id;
		this.clientSecret = clientSecret;
		this.status = status;
		this.amount = amount;
		this.currency = currency;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	public String getClientSecret() { return clientSecret; }
	public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }
	
	public PaymentStatus getStatus() { return status; }
	public void setStatus(PaymentStatus status) { this.status = status; }
	
	public long getAmount() { return amount; }
	public void setAmount(long amount) { this.amount = amount; }
	
	public String getCurrency()	{ return currency; }
	public void setCurrency(String currency) { this.currency = currency; }

	public Map<String, String> getMetadata() { return metadata; }
	public void setMetadata(Map<String, String> metadata) { this.metadata = metadata; }
}
