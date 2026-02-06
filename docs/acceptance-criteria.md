# Acceptance Criteria

---

## Cart – Add Item
**Given** a user is viewing a product  
**When** the user adds the product to the cart  
**Then** the product appears in the cart with quantity 1  

---

## Cart – Update Quantity
**Given** a product exists in the cart  
**When** the user updates the quantity  
**Then** the cart total is updated correctly  

---

## Checkout - Summarize Order
**Given** the user has items in the cart
**When** the order summary is requested
**Then** the summary includes all items and the correct total

---

## Checkout - Update Order
**Given** an order summary
**When** the user changes an item quantity
**Then** the order total is recalculated

---

## Checkout - Process Payment
**Given** an order with valid items and totals
**When** payment is attempted
**Then** payment is requested for the order total

---

## Checkout – Successful Payment
**Given** the user has items in the cart  
**When** payment is successful  
**Then** the order is confirmed
**And** the cart is cleared

---

## Checkout - Insufficient Funds
**Given** a valid order
**When** payment is declined due to insufficient funds
**Then** payment fails
**And** the cart remains unchanged

---

## Checkout – Retry Payment
**Given** a payment attempt has failed
**When** the user retries payment
**Then** another payment attempt is made
**And** the order is not confirmed unless payment succeeds

