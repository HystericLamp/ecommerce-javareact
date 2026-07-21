import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';
import { CheckoutDetailsPage } from '../../pages/CheckoutDetailsPage';

test('AC-CHECKOUT-01 - Get Summary of Order @smoke', async ({ page }) => {
    // Add items to cart
    const shop = new ShopPage(page);
    await shop.goto();
    await shop.product(1).addToCart();
    await shop.product(10).addToCart();

    // Goto Cart and Checkout
    const cart = new CartPage(page);
    await cart.goto();
    
    await expect(cart.item(0).name).toHaveText('Colombian Supremo');
    await expect(cart.item(1).name).toHaveText('Stoneware Latte Mug');

    await cart.checkout();

    // Assert items are visible at checkout summar
    const checkout = new CheckoutDetailsPage(page);

    const checkoutItem1 = checkout.orderItems(0);
    await checkoutItem1.expectQuantity(1);

    const checkoutItem2 = checkout.orderItems(1);
    await checkoutItem2.expectQuantity(1);
});