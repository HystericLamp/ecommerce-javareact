package com.ecommerce.bcruz.dto;

public class PaymentResult
{
	private Long draftOrderId;
    private String clientSecret;

    public PaymentResult(Long draftOrderId, String clientSecret) 
    {
        this.draftOrderId = draftOrderId;
        this.clientSecret = clientSecret;
    }
    

    public Long getDraftOrderId() 
    {
        return draftOrderId;
    }

    public String getClientSecret() 
    {
        return clientSecret;
    }
}
