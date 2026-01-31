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

## Checkout – Successful Payment
**Given** the user has items in the cart  
**When** payment is successful  
**Then** an order confirmation page is displayed  
And the cart is cleared
