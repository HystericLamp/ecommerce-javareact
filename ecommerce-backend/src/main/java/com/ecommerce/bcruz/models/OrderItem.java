package com.ecommerce.bcruz.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem
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
    private Order order;

	public Long getProductId() { return productId; }
	public void setProductId(Long productId) { this.productId = productId; }
	public String getProductName() { return productName; }
	public void setProductName(String productName) { this.productName = productName; }
	public Long getPriceAtPurchase() { return priceAtPurchase; }
	public void setPriceAtPurchase(Long priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }
	public Integer getQuantity() { return quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }
	public Order getOrder() { return this.order; }
	public void setOrder(Order order) { this.order = order; }
}
