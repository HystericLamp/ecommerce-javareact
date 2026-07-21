import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';
import { CheckoutDetailsPage } from '../../pages/CheckoutDetailsPage';

test.beforeEach(async ({ page }) => {
    // Add items to Cart
    const shop = new ShopPage(page);
    await shop.goto();

    await shop.product(1).addToCart();
    await shop.product(10).addToCart();
});

test('AC-CHECKOUT-02 - Update Order', async ({ page }) => {
    // Goto Cart and Checkout
    const cart = new CartPage(page);
    await cart.goto();
    await cart.checkout();

    // Assert initial state
    // Initial quantity state is 1 for each item
    const checkout = new CheckoutDetailsPage(page);
    
    const checkoutItem1 = checkout.orderItems(0);
    await checkoutItem1.expectQuantity(1);
    await checkoutItem1.expectItemTotal("$16.00");

    const checkoutItem2 = checkout.orderItems(1);
    await checkoutItem2.expectQuantity(1);
    await checkoutItem2.expectItemTotal("$22.00");

    // Update items
    await checkoutItem1.setQuantity(3);
    await checkoutItem2.setQuantity(2);

    // Assert change
    await checkoutItem1.expectQuantity(3);
    await checkoutItem1.expectItemTotal("$48.00");

    await checkoutItem2.expectQuantity(2);
    await checkoutItem2.expectItemTotal("$44.00");
});

test('Update Order not allow 0 or negative quantity', async ({ page }) => {
    // Goto Cart and Checkout
    const cart = new CartPage(page);
    await cart.goto();
    await cart.checkout();

    // Assert initial state
    // Initial quantity state is 1 for each item
    const checkout = new CheckoutDetailsPage(page);
    
    const checkoutItem1 = checkout.orderItems(0);
    await checkoutItem1.expectQuantity(1);
    await checkoutItem1.expectItemTotal("$16.00");

    const checkoutItem2 = checkout.orderItems(1);
    await checkoutItem2.expectQuantity(1);
    await checkoutItem2.expectItemTotal("$22.00");

    // Update items
    await checkoutItem1.decrease();
    await checkoutItem2.setQuantity(-2);

    // Assert change
    await checkoutItem1.expectQuantity(1);
    await checkoutItem1.expectItemTotal("$16.00");

    await checkoutItem2.expectQuantity(1);
    await checkoutItem2.expectItemTotal("$22.00");
});