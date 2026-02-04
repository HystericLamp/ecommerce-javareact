# Risk Analysis & Prioritization
This section identifies high-risk areas of the application and
prioritizes testing effort based on business impact and likelihood of failure.

## Risk Table
| AC ID      | Description                              | Risk                                   | Likelihood | Impact | Risk Score | Priority | Mitigation / Test Approach                             |
| ---------- | ---------------------------------------- | -------------------------------------- | ---------- | ------ | ---------- | -------- | ------------------------------------------------------ |
| AC-CART-01 | Add item to cart                         | Item not added due to service failure  | Medium     | High   | 9          | P0       | Unit test verifies CartService.addItem works correctly |
| AC-CART-02 | Add item to cart with quantity           | Quantity not added correctly           | Medium     | Medium | 6          | P1       | Unit test validates addItemWithQuantity                |
| AC-CART-03 | Do not add 0/negative quantity           | Invalid items could corrupt cart       | Low        | Medium | 4          | P2       | Negative test ensures service rejects invalid quantity |
| AC-CART-04 | Adding existing item increments quantity | Quantity might not increment correctly | Medium     | Medium | 6          | P1       | Unit test ensures increment logic works                |
| AC-CART-05 | Update quantity of an item               | Quantity update fails                  | Medium     | High   | 9          | P0       | Unit test ensures updateQuantity works                 |
| AC-CART-06 | Do not update with negative quantity     | Cart may become inconsistent           | Low        | Medium | 4          | P2       | Negative test verifies invalid updates are rejected    |
| AC-CART-07 | Remove item from cart                    | Item may not be removed                | Medium     | High   | 9          | P0       | Unit test verifies removeItemFromCart works            |

## Risk-Based Testing Approach
- P0 (Critical / Show-Stopper) → Anything that prevents the cart or checkout from working.
- P1 (High) → Important rules that improve functionality but don’t completely break the system.
- P2 (Medium / Low) → Edge cases, data validation, safeguards.

