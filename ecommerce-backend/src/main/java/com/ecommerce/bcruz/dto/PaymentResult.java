package com.ecommerce.bcruz.dto;

public class PaymentResult
{
	private Long draftOrderId;
    private String clientSecret;

    public PaymentResult() {}
    public PaymentResult(Long draftOrderId, String clientSecret) 
    {
        this.draftOrderId = draftOrderId;
        this.clientSecret = clientSecret;
    }
    
    public void setDraftOrderId(Long draftOrderId) { this.draftOrderId = draftOrderId; }
    public Long getDraftOrderId() { return draftOrderId; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }
    public String getClientSecret() { return clientSecret; }
}
