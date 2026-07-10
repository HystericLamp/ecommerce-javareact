import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';
import { CheckoutDetailsPage } from '../../pages/CheckoutDetailsPage';

test('AC-CHECKOUT-01 - Get Summary of Order @smoke', async ({ page }) => {
    // Add items to cart
    const shop = new ShopPage(page);
    await shop.goto();
    await shop.product(2).addToCart();
    await shop.product(11).addToCart();

    // Goto Cart and Checkout
    const cart = new CartPage(page);
    await cart.goto();
    
    await expect(cart.item(2).name).toHaveText('Colombian Supremo');
    await expect(cart.item(11).name).toHaveText('Stoneware Latte Mug');

    await cart.checkout();

    // Assert items are visible at checkout summar
    const checkout = new CheckoutDetailsPage(page);

    const checkoutItem2 = checkout.orderItems(2);
    await expect(checkoutItem2.quantity).toHaveValue('1');

    const checkoutItem11 = checkout.orderItems(11);
    await expect(checkoutItem11.quantity).toHaveValue('1');
});