# Requirements & Assumptions

This section captures interpreted requirements and assumptions based on available information.

---

## Product Browsing
**Requirement:**
- Users can view a list of available products

**Assumptions:**
- Product list is paginated
- Products have name, price, and image

**Open Questions:**
- Is search functionality required?

---

## Cart
**Requirement:**
- Users can add and remove products from the cart

**Assumptions:**
- Cart persists during the browser session
- Quantity can be updated from the cart page

**Open Questions:**
- Is there a maximum quantity per product?

---

## Checkout

### Requirements
- Users can complete checkout using a payment provider
- Orders are created before payment confirmation
- Payment status is tracked

### Assumptions
- Guest checkout is allowed
- Single payment provider
- Single currency

### Decisions
- Failed payments can be retried
- Each retry creates a new payment attempt for the same order

