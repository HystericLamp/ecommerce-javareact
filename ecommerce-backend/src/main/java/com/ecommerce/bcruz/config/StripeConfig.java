package com.ecommerce.bcruz.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
@Validated
public class StripeConfig {

    @NotBlank
    private String secretKey;

    @NotBlank
    private String publishableKey;

    public String getSecretKey() 
    {
        return secretKey;
    }

    public void setSecretKey(String secretKey) 
    {
        this.secretKey = secretKey;
    }

    public String getPublishableKey() 
    {
        return publishableKey;
    }

    public void setPublishableKey(String publishableKey) 
    {
        this.publishableKey = publishableKey;
    }
}
