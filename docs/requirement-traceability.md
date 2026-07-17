| Requirement                                        | Scenario                              | Test Case   | Automated | Test File                            | Status |
| -------------------------------------------------- | ------------------------------------- | ----------- | :-------: | ------------------------------------ | :----: |
| RQ-CART-001 Add product to cart                    | TS-CART-001 Add single product        | TC-CART-001 |     ✅     | `tests/e2e/cart/add-item.spec.ts`    | Passed |
| RQ-CART-001 Add product to cart                    | TS-CART-002 Add multiple products     | TC-CART-002 |     ✅     | `tests/e2e/cart/add-item.spec.ts`    | Passed |
| RQ-CART-002 Remove product                         | TS-CART-005 Remove product            | TC-CART-006 |     ✅     | `tests/e2e/cart/remove-item.spec.js` | Passed |
| RQ-CART-003 Update quantity                        | TS-CART-003 Increase quantity         | TC-CART-003 |     ✅     | `tests/e2e/cart/quantity.spec.ts`    | Passed |
| RQ-CART-003 Update quantity                        | TS-CART-006 Cart total recalculates   | TC-CART-005 |     ✅     | `tests/e2e/cart/quantity.spec.js`    | Passed |
| RQ-CART-004 Remove item when quantity reaches zero | TS-CART-004 Decrease quantity to zero | TC-CART-004 |     ✅     | `tests/e2e/cart/quantity.spec.js`    | Passed |
