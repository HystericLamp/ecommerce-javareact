package com.ecommerce.bcruz.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DraftOrderItem
{
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name = "product_id", nullable = false)
    private Long productId;
	
	@Column(nullable = false)
    private String productName;
	
	@Column(name = "price_at_purchase", nullable = false)
    private Long priceAtPurchase;
	
	@Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private DraftOrder draftOrder;

	public Long getProductId() { return productId; }
	public void setProductId(Long productId) { this.productId = productId; }
	public String getProductName() { return productName; }
	public void setProductName(String productName) { this.productName = productName; }
	public Long setPriceAtCheckout() { return priceAtPurchase; }
	public void setPriceAtCheckout(Long priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }
	public Integer getQuantity() { return quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }
	public DraftOrder getDraftOrder() { return this.draftOrder; }
	public void setDraftOrder(DraftOrder draftOrder) { this.draftOrder = draftOrder; }
}
