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

    await shop.product(2).addToCart();
    await shop.product(11).addToCart();
});

test('AC-CHECKOUT-05 - Retry Payment', async ({ page }) => {
    // First make Payment fail
    const cart = new CartPage(page);
    await cart.goto();
    await cart.checkout();

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

    const checkoutPayment = new CheckoutPaymentPage(page);
    const stripePaymentForm = checkoutPayment.stripePaymentForm();

    await stripePaymentForm.fillCard({
        number: '4000000000009995',
        expiry: '1234',
        cvc: '123',
        zip: '12345',
    });

    await stripePaymentForm.submitPayment();

    await expect(
        page.getByTestId('payment-error')
    ).toContainText('Your card has insufficient funds. Try a different card.');

    await page.waitForURL(/checkoutpayment/, {
        timeout: 15000,
    });

    // Retry payment is successful
    await stripePaymentForm.fillCard({
        number: '4242424242424242',
        expiry: '1234',
        cvc: '123',
        zip: '12345',
    });

    await stripePaymentForm.submitPayment();
    
    // Assert success
    await expect(page).toHaveURL('/checkoutsuccess');
    const checkoutSuccess = new CheckoutSuccessPage(page);

    await checkoutSuccess.expectPaymentSuccessMsg();
    await expect(checkoutSuccess.continueShoppingBtn).toBeVisible();
    await expect(checkoutSuccess.viewOrdersBtn).toBeVisible();
});