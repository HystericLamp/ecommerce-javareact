# Product Backlog

## Epic-01: Shopping Cart

## US-01 - Add Item to Cart
As a customer  
I want to add items to my cart  
So that I can specify what I want to buy

**Acceptance Criteria**:  
**Given** a user is viewing an item  
**When** the user adds the item to the cart  
**Then** the item appears in the cart with quantity of 1  

---

## US-02 - Update Quantity
As a customer  
I want to update items in my cart  
So that I can control what I am about to purchase

**Acceptance Criteria**:  
**Given** a product exists in the cart   
**When** the user updates the quantity  
**Then** the cart total is updated correctly  

---

## US-03 - Remove Item
As a customer  
I want to remove items from my cart  
So that I can remove items I no longer wish to purchase  

**Acceptance Criteria**:  
**Given** a product exists in the cart   
**When** the user deletes item from cart  
**Then** item is removed from cart 

---

## Epic-02: Payment & Checkout

## US-04 - View Order Summary
As a customer  
I want to view a summary of items I'm about to purchase  
So that I can make informed decisions on my purchse

**Acceptance Criteria**:  
**Given** the user has items in the cart  
**When** the order summary is requested  
**Then** the summary includes all items and the correct total  

---

## US-05 - Update Order
As a customer  
I want to update an item's quantity before I order  
So that I can control what I'm about to purchase  

**Acceptance Criteria**:  
**Given** an order summary  
**When** the user changes an item quantity  
**Then** the order total is recalculated  

---

## US-06 - Remove Item from Order
As a customer  
I want to remove an item before I order  
So that I don't have to pay for an item I no longer want  

**Acceptance Criteria**:  
**Given** an order summary  
**When** the user removes an item from order  
**Then** the order total is recalculated  

---

## US-07 - Process Payment
As a customer  
I want to use my money to pay for my order  
So that I can claim items I will buy  

**Acceptance Criteria**:  
**Given** an order with valid items and totals  
**When** payment is attempted  
**Then** payment is requested for the order total  

---

## US-08 - Successful Payment
As a customer  
I want to see a successful payment after I spend money  
So that I can confirm my money was used for the purchase  

**Acceptance Criteria**:  
**Given** the user has items in the cart  
**When** payment is successful  
**Then** the order is confirmed  
**And** the cart is cleared  

---

## US-09 – Handle Payment Failure
As a customer  
I want to be notified when payment fails  
So that I can take corrective action  

**Acceptance Criteria**:  
**Given** a valid order  
**When** payment is declined due to an error    
**Then** payment fails  
**And** the cart remains unchanged  

---

## US-10 - Retry Payment
As a customer  
I want to retry purchasing the order after a failed attempt  
So that I try again at purchasing items I already selected  

**Acceptance Criteria**:  
**Given** a payment attempt has failed  
**When** the user retries payment  
**Then** another payment attempt is made  
**And** the order is not confirmed unless payment succeeds

---

## Epic-03: Product Catalog

## US-11 - Navigate Site
As a customer  
I want to navigate through site to browse products  
So that I have a pleasant and seamless shopping experience   

## Site - Navigation
**Acceptance Criteria**:  
**Given** The user is looking at the site   
**When** user wants to go to a different page  
**Then** user is sent to desired site page  

---

## US-12 - Browse Shop
As a customer  
I want to see all available shop items  
So that I can browse all available items to buy  

**Acceptance Criteria**:  
**Given** The user is at the site  
**When** the user opens the menu  
**Then** show all available items for purchase  

---
