| AC ID | Description | Risk | Unit | API/Integration | E2E | Status |
|-------|-------------|------|------|-----------------|-----|--------|
| AC-CART-01 | Add item to cart | P0 | `service.tests.CartServiceTest` | None | `tests.e2e.cart.add-item` | DONE |
| AC-CART-02 | Add item to cart with quantity | P1 | `service.tests.CartServiceTest` | None | None | DONE |
| AC-CART-03 | Do not add an item with 0 or negative quantity | P2 | `service.tests.CartServiceTest` | None | `tests.e2e.cart.add-item` | DONE |
| AC-CART-04 | Adding existing item into the cart should increment quantity | P1 | `service.tests.CartServiceTest` | None | `tests.e2e.cart.add-item` | DONE |
| AC-CART-05 | Update quantity of an item in cart | P0 | `service.tests.CartServiceTest` | None | `tests.e2e.cart.quantity.spec.js` | DONE |
| AC-CART-06 | Do not update an item with a negative quantity or non-existent item | P2 | `service.tests.CartServiceTest` | None | `tests.e2e.cart.quantity.spec.js` | DONE |
| AC-CART-07 | Remove item from cart | P0 | `service.tests.CartServiceTest` | None | `tests.e2e.cart.remove-item.spec.js` | DONE |
| AC-CHECKOUT-01 | Get summary of order | P0 | `service.tests.CheckoutServiceTest` | None | `tests.e2e.checkout.order-summary.spec.js` | DONE |
| AC-CHECKOUT-02 | Update order | P0 | None | None | `tests.e2e.checkout.update-order.spec.js` | DONE |
| AC-CHECKOUT-03 | Process payment | P0 | `service.tests.WebhookServiceTest` | `controller.tests.WebhookControllerTest` | `tests.e2e.checkout.payment.spec.js` | DONE |
| AC-CHECKOUT-04 | Failed payment | P1 | None | None | `tests.e2e.checkout.payment-fail.spec.js` | DONE |
| AC-CHECKOUT-05 | Retry payment | P0 | None | None | `tests.e2e.checkout.payment-retry.spec.js` | DONE |
| AC-PAYMENT-INTEGRATION-01 | Process successful payment | P1 | None | `infrastructure.payment.tests.StripePaymentProcessorIntegrationTest` | None | DONE |
| AC-PAYMENT-INTEGRATION-02 | Refund payment | P1 | None | `infrastructure.payment.tests.StripePaymentProcessorIntegrationTest` | None | DONE |
| AC-USER-01 | Register new member | P0 | `service.tests.UserServiceTest` | `controller.tests.UserControllerTest` | `tests.e2e.user.user-login` | DONE |
| AC-USER-02 | Login as an existing user | P0 | `auth.tests.AuthServiceTest` | `controller.tests.AuthControllerTest` | `tests.e2e.user.user-login` | DONE |
| AC-PRODUCT-MENU-01 | Get all product information | P0 | `service.tests.ProductServiceTest` | `controller.tests.ProductControllerTest` | `tests.e2e.shop.product-info.spec.js` | DONE |