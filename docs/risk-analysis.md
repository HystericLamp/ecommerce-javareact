# Risk Analysis & Prioritization
This section identifies high-risk areas of the application and
prioritizes testing effort based on business impact and likelihood of failure.

## Risk Table
| AC / US ID             | Description                    | Risk                                                               | Likelihood | Impact | Risk Score | Priority | Mitigation / Test Approach                                                                     |
| ---------------------- | ------------------------------ | ------------------------------------------------------------------ | ---------- | ------ | ---------- | -------- | ---------------------------------------------------------------------------------------------- |
| US-01 / AC-01          | Add item to cart               | Item not added to cart due to backend/service failure              | Medium     | High   | 6          | P0       | System-level test validates item addition in cart and correct quantity; error handling tested  |
| US-01 / AC-02          | Add item to cart with quantity | Quantity not updated correctly                                     | Medium     | Medium | 4          | P1       | Boundary testing for quantity values; negative quantity rejected; UI reflects correct quantity |
| US-02 / AC-03          | Update item quantity in cart   | Cart totals may not recalculate                                    | Medium     | High   | 6          | P0       | Test quantity updates; validate totals in cart UI and API response                             |
| US-02 / AC-04          | Update with invalid quantity   | Cart may enter inconsistent state                                  | Low        | Medium | 2          | P2       | Negative testing ensures invalid values are rejected gracefully                                |
| US-03 / AC-05          | Remove item from cart          | Item may not be removed; cart totals incorrect                     | Medium     | High   | 6          | P0       | Remove item scenario tested in UI and API; totals validated                                    |
| US-04 / AC-06          | View order summary             | Summary may show incorrect items or totals                         | Medium     | High   | 6          | P0       | System tests for summary accuracy; cross-check cart → checkout                                 |
| US-05 / AC-07          | Update order in checkout       | Changes may not reflect in totals                                  | Medium     | High   | 6          | P0       | Test updates in checkout flow; ensure totals sync with cart                                    |
| US-06 / AC-08          | Remove item from order         | Checkout may show removed item incorrectly                         | Low        | Medium | 2          | P2       | Validate UI and backend consistency                                                            |
| US-07 / AC-09          | Process payment                | Payment request fails or duplicates                                | Medium     | High   | 6          | P0       | End-to-end payment flow tested; duplicate prevention verified; API response validated          |
| US-08 / AC-10          | Successful payment             | Order confirmation not generated or cart not cleared               | Low        | High   | 3          | P1       | Test full flow; confirm order creation; cart reset validated                                   |
| US-09 / AC-11          | Insufficient funds             | Payment fails but order marked as confirmed                        | Low        | High   | 3          | P1       | Negative payment test; validate UI message; order not created                                  |
| US-10 / AC-12          | Retry payment                  | Multiple retries may cause inconsistent totals or duplicate orders | Medium     | High   | 6          | P0       | Test retry flow; confirm idempotency and total calculation                                     |
| R-01 (Cross-FE)        | Cart → Checkout consistency    | Quantities or totals may differ between cart and checkout          | Medium     | High   | 6          | P0       | Cross-feature system test validates state integrity                                            |
| R-02 (Non-Functional)  | Network / service failures     | Requests fail mid-operation causing inconsistent state             | Medium     | High   | 6          | P0       | Simulate network errors; validate error messages and recovery                                  |
| R-03 (Product Catalog) | Navigation / search            | Incorrect page or missing products                                 | Low        | Medium | 2          | P2       | Test navigation paths; validate links and product listings                                     |


## Risk-Based Testing Approach
- P0 (Critical / Show-Stopper) → Anything that prevents the cart or checkout from working.
- P1 (High) → Important rules that improve functionality but don’t completely break the system.
- P2 (Medium / Low) → Edge cases, data validation, safeguards.

