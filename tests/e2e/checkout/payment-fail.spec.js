import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';
import { CheckoutSuccessPage } from '../../pages/CheckoutSuccessPage';
import { CheckoutDetailsPage } from '../../pages/CheckoutDetailsPage';
import { CheckoutPaymentPage } from '../../pages/CheckoutPaymentPage';

test.beforeEach(async ({ page, context }) => {
    // Clear state
    await context.clearCookies();

    await page.goto('/');

    await page.evaluate(() => {
        localStorage.clear();
        sessionStorage.clear();
    });

    // Add items to Cart
    const shop = new ShopPage(page);
    await shop.goto();

    await shop.product(1).addToCart();
    await shop.product(10).addToCart();
});

test('AC-CHECKOUT-04 - Failed Payment', async ({ page }) => {
    // Go to checkout page
    const cart = new CartPage(page);
    await cart.goto();
    await cart.checkout();

    // Fill in Demo details and continue to payment page
    const checkout = new CheckoutDetailsPage(page);
    const customerForm = checkout.customerForm();
    await customerForm.setFirstname("First");
    await customerForm.setLastname("Last");
    await customerForm.setEmail("customer@example.com");
    await customerForm.setAddress1("123 Main St");
    await customerForm.setCity("City");
    await customerForm.setProvince("Province");
    await customerForm.setPostalcode("T2P 1J9");
    await customerForm.setCountry("Country");

    const orderSummary = checkout.orderSummary();
    await orderSummary.acceptOrder();

    // payment processor returns failure
    const checkoutPayment = new CheckoutPaymentPage(page);
    const stripePaymentForm = checkoutPayment.stripePaymentForm();

    await stripePaymentForm.fillCard({
        number: '4000000000009995',
        expiry: '1234',
        cvc: '123',
        zip: '12345',
    });

    await stripePaymentForm.submitPayment();

    await expect(page).toHaveURL(/checkoutpayment/);
    await expect(
        page.getByTestId('payment-error')
    ).toContainText('Your card has insufficient funds. Try a different card.');

    // Go to cart to check cart is not empty
    await cart.goto();

    await expect(page.getByText('Your Cart')).toBeVisible();
    const item1 = cart.item(0).name;
    await expect(item1).toBeVisible();

    const item2 = cart.item(1).name;
    await expect(item2).toBeVisible();
});