# Product Backlog

## Epic: Shopping Cart

## US-01 - Manage Cart
As a customer  
I want to add and update items in my cart  
So that I can control what I purchase

## Cart – Add Item
**Acceptance Criteria**:  
**Given** a user is viewing a product  
**When** the user adds the product to the cart  
**Then** the product appears in the cart with quantity 1  

---

## Cart – Update Quantity
**Acceptance Criteria**:  
**Given** a product exists in the cart   
**When** the user updates the quantity  
**Then** the cart total is updated correctly  

---

## Cart – Remove Product
**Acceptance Criteria**:  
**Given** a product exists in the cart   
**When** the user deletes product from cart  
**Then** product is removed from cart 

---

## US-02 - Complete Checkout
As a customer  
I want to complete payment for my order  
So that I can purchase my items

## Checkout - Summarize Order
**Acceptance Criteria**:  
**Given** the user has items in the cart  
**When** the order summary is requested  
**Then** the summary includes all items and the correct total  

---

## Checkout - Update Order
**Acceptance Criteria**:  
**Given** an order summary  
**When** the user changes an item quantity  
**Then** the order total is recalculated  

---

## Checkout - Process Payment
**Acceptance Criteria**:  
**Given** an order with valid items and totals  
**When** payment is attempted  
**Then** payment is requested for the order total  

---

## Checkout – Successful Payment
**Acceptance Criteria**:  
**Given** the user has items in the cart  
**When** payment is successful  
**Then** the order is confirmed
**And** the cart is cleared

---

## Checkout - Insufficient Funds
**Acceptance Criteria**:  
**Given** a valid order  
**When** payment is declined due to insufficient funds  
**Then** payment fails  
**And** the cart remains unchanged

---

## Checkout – Retry Payment
**Acceptance Criteria**:  
**Given** a payment attempt has failed  
**When** the user retries payment  
**Then** another payment attempt is made  
**And** the order is not confirmed unless payment succeeds  