package com.ecommerce.bcruz.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "draft_orders")
public class DraftOrder
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "user_id")
    private Long userId;
	
	@Column(name = "total")
    private Long totalAmountInCents;
	
	@Column(name = "currency")
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DraftOrderStatus status;
    
    @Column(name = "payment_intent_id")
    private String paymentIntentId;

    @OneToMany(mappedBy = "draftOrder", cascade = CascadeType.ALL)
    private List<DraftOrderItem> items;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
	public Long getTotalAmountInCents() { return totalAmountInCents; }
	public void setTotalAmountInCents(Long totalAmountInCents) { this.totalAmountInCents = totalAmountInCents; }
	public String getCurrency() { return currency; }
	public void setCurrency(String currency) { this.currency = currency; }
	public DraftOrderStatus getStatus() { return status; }
	public void setStatus(DraftOrderStatus status) { this.status = status; }
	public String getPaymentIntentId() { return paymentIntentId; }
	public void setPaymentIntentId(String paymentIntentId) { this.paymentIntentId = paymentIntentId; }
	public List<DraftOrderItem> getItems() { return items; }
	public void setItems(List<DraftOrderItem> items) { this.items = items; }
}
