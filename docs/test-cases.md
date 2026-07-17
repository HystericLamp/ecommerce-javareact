# Test Cases

| ID          | Requirement | Scenario    | Priority | Automated |
| ----------- | ----------- | ----------- | -------- | --------- |
| TC-CART-001 | RQ-CART-001 | TS-CART-001 | High     | Yes       |
| TC-CART-002 | RQ-CART-001 | TS-CART-002 | High     | Yes       |
| TC-CART-003 | RQ-CART-003 | TS-CART-003 | High     | Yes       |
| TC-CART-004 | RQ-CART-004 | TS-CART-004 | Medium   | Yes       |
| TC-CART-005 | RQ-CART-003 | TS-CART-006 | High     | Yes       |
| TC-CART-006 | RQ-CART-002 | TS-CART-005 | High     | Yes       |
| TC-PROD-001 | RQ-PROD-001 | TS-PROD-001 | High     | Yes       |
| TC-PROD-002 | RQ-PROD-001 | TS-PROD-002 | Medium   | Yes       |
| TC-PROD-003 | RQ-PROD-001 | TS-PROD-003 | Medium   | Yes       |
| TC-PROD-004 | RQ-PROD-002 | TS-PROD-004 | High     | Yes       |
| TC-AUTH-001 | RQ-AUTH-001 | TS-AUTH-001 | High     | Yes       |
| TC-AUTH-002 | RQ-AUTH-001 | TS-AUTH-002 | High     | Yes       |
| TC-AUTH-003 | RQ-AUTH-002 | TS-AUTH-003 | High     | Yes       |
| TC-CHK-001  | RQ-CHK-001  | TS-CHK-001  | High     | Yes       |
| TC-CHK-002  | RQ-CHK-001  | TS-CHK-002  | High     | Yes       |
| TC-CHK-003  | RQ-CHK-001  | TS-CHK-003  | High     | Yes       |
| TC-CHK-004  | RQ-CHK-001  | TS-CHK-007  | Medium   | Yes       |
| TC-CHK-005  | RQ-CHK-002  | TS-CHK-004  | High     | Yes       |
| TC-CHK-006  | RQ-CHK-003  | TS-CHK-005  | High     | Yes       |
| TC-CHK-007  | RQ-CHK-004  | TS-CHK-006  | Medium   | Yes       |

---

# Cart

## TC-CART-001 - Add Product to Cart

### Requirement
RQ-CART-001

### Scenario
TS-CART-001

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/e2e/cart/add-item.spec.ts`

### Preconditions

- User is viewing the product listing.
- Cart is empty.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Click **Add to Cart** on a product. |
| 2 | Open the shopping cart. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Cart badge increments to 1. |
| 2 | Product appears in the cart with the correct quantity and price. |

---

## TC-CART-002 - Add Multiple Products to Cart

### Requirement
RQ-CART-001

### Scenario
TS-CART-002

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/e2e/cart/add-item.spec.ts`

### Preconditions

- User is viewing the product listing.
- Cart is empty.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Click **Add to Cart** on the first product. |
| 2 | Click **Add to Cart** on a second product. |
| 3 | Open the shopping cart. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | First product is added to the cart. |
| 2 | Second product is added and cart badge increments to 2. |
| 3 | Both products are displayed with the correct quantities and prices. |

---

## TC-CART-003 - Update Product Quantity

### Requirement
RQ-CART-003

### Scenario
TS-CART-003

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/e2e/cart/quantity.spec.ts`

### Preconditions

- Cart contains at least one product.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Increase the quantity of a product to 2. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Product quantity updates to 2. |

---

## TC-CART-004 - Decrease Product Quantity to Zero

### Requirement
RQ-CART-004

### Scenario
TS-CART-004

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/e2e/cart/quantity.spec.js`

### Preconditions

- Cart contains a product with quantity 1.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Decrease the product quantity from 1 to 0 (or click the decrement control). |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Product is removed from the cart, and the cart badge and total are updated accordingly. |

---

## TC-CART-005 - Cart Total Updates After Quantity Change

### Requirement
RQ-CART-003

### Scenario
TS-CART-006

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/e2e/cart/quantity.spec.js`

### Preconditions
- Cart contains a product with quantity 1.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Increase the product quantity. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Cart total updates immediately to reflect the new quantity. |

---

## TC-CART-006 - Remove Product from Cart

### Requirement
RQ-CART-002

### Scenario
TS-CART-005

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/e2e/cart/remove-item.spec.js`

### Preconditions

- Cart contains at least one product.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Remove a product from the cart. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Product is removed and the cart badge and total are updated accordingly. |

---

# Product Browsing

## TC-PROD-001 - View Product List

### Requirement
RQ-PROD-001

### Scenario
TS-PROD-001

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Application is available.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Navigate to the product listing page. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | A paginated list of available products is displayed. |

---

## TC-PROD-002 - Load Products During Slow API Response

### Requirement
RQ-PROD-001

### Scenario
TS-PROD-002

### Priority
Medium

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- API response is artificially delayed.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Open the product listing page. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Application remains responsive and products appear once the response completes. |

---

## TC-PROD-003 - Handle Empty Product List

### Requirement
RQ-PROD-001

### Scenario
TS-PROD-003

### Priority
Medium

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Product API returns no products.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Open the product listing page. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | An empty state is displayed instead of a product list. |

---

## TC-PROD-004 - Verify Product Details

### Requirement
RQ-PROD-002

### Scenario
TS-PROD-004

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Product list contains one or more products.

### Test Steps

| Step | Action |
|------|--------|
| 1 | View the product listing. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Each product displays its name, price, and image. |

---

# Authentication

## TC-AUTH-001 - Successful Login

### Requirement
RQ-AUTH-001

### Scenario
TS-AUTH-001

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Registered user account exists.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Enter valid email and password. |
| 2 | Click **Sign In**. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Credentials are accepted. |
| 2 | User is successfully signed in. |

---

## TC-AUTH-002 - Invalid Login

### Requirement
RQ-AUTH-001

### Scenario
TS-AUTH-002

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Registered user account exists.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Enter an invalid email or password. |
| 2 | Click **Sign In**. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Invalid credentials are entered. |
| 2 | Login is rejected and an error message is displayed. |

---

## TC-AUTH-003 - Logout

### Requirement
RQ-AUTH-002

### Scenario
TS-AUTH-003

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- User is signed in.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Click **Sign Out**. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | User is signed out and returned to the appropriate page. |

---

# Checkout

## TC-CHK-001 - Successful Checkout

### Requirement
RQ-CHK-001

### Scenario
TS-CHK-001

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Cart contains one or more products.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Proceed to checkout. |
| 2 | Enter valid payment details. |
| 3 | Confirm the purchase. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Checkout page is displayed. |
| 2 | Payment is authorized successfully. |
| 3 | Order confirmation is displayed. |

---

## TC-CHK-002 - Declined Payment

### Requirement
RQ-CHK-001

### Scenario
TS-CHK-002

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Cart contains one or more products.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Proceed to checkout. |
| 2 | Enter payment details that will be declined. |
| 3 | Confirm the purchase. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Checkout page is displayed. |
| 2 | Payment details are accepted for processing. |
| 3 | Checkout fails and an appropriate error message is displayed. |

---

## TC-CHK-003 - Checkout with Empty Cart

### Requirement
RQ-CHK-001

### Scenario
TS-CHK-003

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Cart is empty.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Attempt to proceed to checkout. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Checkout is prevented and the user is informed the cart is empty. |

---

## TC-CHK-004 - Checkout During Network Interruption

### Requirement
RQ-CHK-001

### Scenario
TS-CHK-007

### Priority
Medium

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Cart contains products.
- Network interruption is simulated.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Proceed to checkout. |
| 2 | Submit payment during the interruption. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Checkout page is displayed. |
| 2 | User receives an appropriate error and no duplicate order is created. |

---

## TC-CHK-005 - Order Created Before Payment Confirmation

### Requirement
RQ-CHK-002

### Scenario
TS-CHK-004

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Cart contains one or more products.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Begin checkout. |
| 2 | Submit payment. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | Checkout begins successfully. |
| 2 | Order is created before payment authorization completes. |

---

## TC-CHK-006 - Payment Status Recorded

### Requirement
RQ-CHK-003

### Scenario
TS-CHK-005

### Priority
High

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Checkout has been initiated.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Complete payment processing. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | The order records the correct payment status. |

---

## TC-CHK-007 - Retry Failed Payment

### Requirement
RQ-CHK-004

### Scenario
TS-CHK-006

### Priority
Medium

### Automation
- Status: Automated
- Framework: Playwright
- Test file: `tests/cart.spec.ts`

### Preconditions

- Previous payment attempt failed.

### Test Steps

| Step | Action |
|------|--------|
| 1 | Select **Retry Payment**. |
| 2 | Enter valid payment details. |
| 3 | Confirm the purchase. |

### Expected Results

| Step | Expected Result |
|------|-----------------|
| 1 | A new payment attempt is created. |
| 2 | Payment is authorized successfully. |
| 3 | Checkout completes successfully and the existing order is updated. |

---

