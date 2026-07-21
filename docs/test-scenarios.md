# Test Scenarios

This document defines the high-level test scenarios derived from the functional requirements.

---

# Product Browsing

| ID | Requirement | Test Scenario | Priority |
|----|-------------|---------------|----------|
| TS-PROD-001 | RQ-PROD-001 | Verify the product list is displayed successfully. | High |
| TS-PROD-002 | RQ-PROD-002 | Verify each product displays its name and price | High |

---

# Shopping Cart

| ID | Requirement | Test Scenario | Priority |
|----|-------------|---------------|----------|
| TS-CART-001 | RQ-CART-001 | Verify a user can add a product to the cart. | High |
| TS-CART-002 | RQ-CART-001 | Verify a user can add multiple products to the cart. | High |
| TS-CART-003 | RQ-CART-003 | Verify a user can update the quantity of a product. | High |
| TS-CART-004 | RQ-CART-004 | Verify a user cannot have an item quantity of 0 or less. | Medium |
| TS-CART-005 | RQ-CART-002 | Verify a user can remove a product from the cart. | High |
| TS-CART-006 | RQ-CART-003 | Verify cart totals update after quantity changes. | High |

---

# Authentication

| ID | Requirement | Test Scenario | Priority |
|----|-------------|---------------|----------|
| TS-AUTH-001 | RQ-AUTH-001 | Verify a registered user can log in with valid credentials. | High |
| TS-AUTH-002 | RQ-AUTH-001 | Verify login fails with invalid credentials. | High |
| TS-AUTH-003 | RQ-AUTH-002 | Verify a logged-in user can log out successfully. | High |

---

# Checkout

| ID | Requirement | Test Scenario | Priority |
|----|-------------|---------------|----------|
| TS-CHK-001 | RQ-CHK-001 | Verify checkout succeeds with valid payment details. | High |
| TS-CHK-002 | RQ-CHK-001 | Verify checkout fails when payment is declined. | High |
| TS-CHK-003 | RQ-CHK-001 | Verify checkout cannot proceed with an empty cart. | High |
| TS-CHK-004 | RQ-CHK-002 | Verify an order is created before payment authorization completes. | High |
| TS-CHK-005 | RQ-CHK-003 | Verify payment status is updated after payment processing. | High |
| TS-CHK-006 | RQ-CHK-004 | Verify users can retry checkout after a failed payment. | Medium |
| TS-CHK-007 | RQ-CHK-001 | Verify checkout behavior during a network interruption. | Medium |