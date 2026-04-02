package com.ecommerce.bcruz.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total", nullable = false)
    private Long total;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_intent_id")
    private String paymentIntentId;

    @Column(name = "currency")
    private String currency;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
	public Long getTotalAmountInCents() { return total; }
	public void setTotalAmountInCents(Long total) { this.total = total; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	public String getStatus() { return status; }
	public void setStatus(OrderStatus status) { this.status = status.toString(); }
	public String getPaymentIntentId() { return paymentIntentId; }
	public void setPaymentIntentId(String paymentIntentId) { this.paymentIntentId = paymentIntentId; }
	public String getCurrency() { return currency; }
	public void setCurrency(String currency) { this.currency = currency; }
	public List<OrderItem> getItems() { return items; }
	public void setItems(List<OrderItem> items) { this.items = items; }
}
