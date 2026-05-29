| AC ID | Description | Risk | Unit | API/Integration | E2E | Status |
|------|-------------|------|----------------|-------|--------|
| AC-CART-01 | Add item to cart | P0 | service.tests.CartServiceTest | None | tests.e2e.cart.add-item | DONE |
| AC-CART-02 | Add item to cart with quantity | P1 | service.tests.CartServiceTest | None | None | DONE |
| AC-CART-03 | Do not add an item with 0 or negative quantity | P2 | service.tests.CartServiceTest | None | tests.e2e.cart.add-item | DONE |
| AC-CART-04 | Adding existing Item into the Cart should increment quantity | P1 | service.tests.CartServiceTest | None | tests.e2e.cart.add-item | DONE |
| AC-CART-05 | Update quantity of an Item in Cart | P0 | service.tests.CartServiceTest | None | tests.e2e.cart.quantity.spec.js | DONE |
| AC-CART-06 | Do not update an item quantity with a negative quantity or non-existent item | P2 | service.tests.CartServiceTest | None | tests.e2e.cart.quantity.spec.js | DONE |
| AC-CART-07 | Remove item from cart | P0 | service.tests.CartServiceTest | None | tests.e2e.cart.remove-item.spec.js | DONE |
| AC-CHECKOUT-01 | Get Summary of Order | P0 | service.tests.CheckoutServiceTest | None | WIP | WIP |
| AC-CHECKOUT-02 | Update Order | P0 | None | None | WIP | WIP |
| AC-CHECKOUT-03 | Process Payment | P0 | service.tests.WebhookServiceTest | controller.tests.WebhookControllerTest | WIP | DONE |
| AC-CHECKOUT-04 | Failed Payment | P1 | None | None | WIP | WIP |
| AC-CHECKOUT-05 | Retry Payment | P0 | None | None | WIP | WIP |
| AC-PAYMENT-INTEGRATION-01 | Process Successful Payment | P1 | None | infrastructure.payment.tests.StripePaymentProcessorIntegrationTest | None | DONE |
| AC-PAYMENT-INTEGRATION-02 | Refund Payment | P1 | None | infrastructure.payment.tests.StripePaymentProcessorIntegrationTest | None | DONE |
| AC-USER-01 | Register new Member | P0 | service.tests.UserServiceTest | controller.tests.UserControllerTest | tests.e2e.user.user-login | Done |
| AC-USER-02 | Login as an Existing User | P0 | auth.tests.AuthServiceTest | controller.tests.AuthControllerTest | tests.e2e.user.user-login | Done |
| AC-PRODUCT-MENU-01 | Get All Product Information | P0 | service.tests.ProductServiceTest | controller.tests.ProductControllerTest | tests.e2e.product-menu.product-info.spec.js | Done |