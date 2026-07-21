# Requirements & Assumptions

This document captures the functional requirements, assumptions, and open questions for the ecommerce application.

---

# Product Browsing

## Functional Requirements

| ID | Requirement |
|----|-------------|
| RQ-PROD-001 | Users can view a list of available products. |
| RQ-PROD-002 | Each product displays a name and price. |

## Assumptions

- Products are displayed in a consistent order.
- Product images are available for all products.

## Open Questions

- Is search functionality required?
- Should users be able to sort products?

---

# Shopping Cart

## Functional Requirements

| ID | Requirement |
|----|-------------|
| RQ-CART-001 | Users can add a product to the shopping cart. |
| RQ-CART-002 | Users can remove a product from the shopping cart. |
| RQ-CART-003 | Users can update the quantity of products in the cart. |
| RQ-CART-004 | Users cannot have an item quantity at 0 or less |

## Assumptions

- Product quantity cannot be less than one.
- Updating the quantity immediately updates the cart total.

## Open Questions

- Is there a maximum quantity allowed per product?
- Should cart contents persist after the user logs out?

---

# Authentication

## Functional Requirements

| ID | Requirement |
|----|-------------|
| RQ-AUTH-001 | Registered users can sign in using valid credentials. |
| RQ-AUTH-002 | Signed-in users can sign out of the application. |

## Assumptions

- Authentication uses email and password.
- Invalid credentials display an error message.

## Open Questions

- Are password reset and account registration supported?

---

# Checkout

## Functional Requirements

| ID | Requirement |
|----|-------------|
| RQ-CHK-001 | Users can complete checkout after successful payment authorization. |
| RQ-CHK-002 | An order is created before payment confirmation. |
| RQ-CHK-003 | Payment status is recorded for each order. |
| RQ-CHK-004 | Users can retry a failed payment attempt. |

## Assumptions

- Guest checkout is allowed.
- A single payment provider is supported.
- Transactions use a single currency.

## Business Decisions

- Each payment retry creates a new payment attempt associated with the same order.