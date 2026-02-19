| AC ID | Description | Risk | Implementation | Tests | Status |
|------|-------------|------|----------------|-------|--------|
| AC-CART-01 | Add item to cart | P0 | businesslogic.cart.CartService | businesslogic.cart.service.CartServiceTests | DONE |
| AC-CART-02 | Add item to cart with quantity | P1 | businesslogic.cart.CartService | businesslogic.cart.service.CartServiceTests | DONE |
| AC-CART-03 | Do not add an item with 0 or negative quantity (possibly negate on front-end) | P2 | businesslogic.cart.CartService | businesslogic.cart.service.CartServiceTests | DONE |
| AC-CART-04 | Adding existing Item into the Cart should increment quantity | P1 | businesslogic.cart.CartService | businesslogic.cart.service.CartServiceTests | DONE |
| AC-CART-05 | Update quantity of an Item in Cart | P0 | businesslogic.cart.CartService | businesslogic.cart.service.CartServiceTests | DONE |
| AC-CART-06 | Do not update an item quantity with a negative quantity or non-existent item | P2 | businesslogic.cart.CartService | businesslogic.cart.service.CartServiceTests | DONE |
| AC-CART-07 | Remove item from cart | P0 | businesslogic.cart.CartService | businesslogic.cart.service.CartServiceTests | DONE |
| AC-CHECKOUT-01 | Get Summary of Order | P0 | businesslogic.checkout.CheckoutService | businesslogic.checkout.CheckoutServiceTest | DONE |
| AC-CHECKOUT-02 | Update Order | P0 | businesslogic.checkout.CheckoutService | businesslogic.checkout.CheckoutServiceTest | DONE |
| AC-CHECKOUT-03 | Process Payment | P0 | businesslogic.checkout.CheckoutService | businesslogic.checkout.CheckoutServiceTest | DONE |
| AC-CHECKOUT-04 | Failed Payment | P1 | businesslogic.checkout.CheckoutService | businesslogic.checkout.CheckoutServiceTest | DONE |
| AC-CHECKOUT-05 | Retry Payment | P0 | businesslogic.checkout.CheckoutService | businesslogic.checkout.CheckoutServiceTest | DONE |
| AC-PAYMENT-INTEGRATION-01 | Process Successful Payment | P1 | com.ecommerce.bcruz.infrastructure.StripePaymentProcessor | infrastructure.payment.StripePaymentProcessorIntegrationTest.java | DONE |
| AC-PAYMENT-INTEGRATION-02 | Refund Payment | P1 | com.ecommerce.bcruz.infrastructure.StripePaymentProcessor | infrastructure.payment.StripePaymentProcessorIntegrationTest.java | DONE |