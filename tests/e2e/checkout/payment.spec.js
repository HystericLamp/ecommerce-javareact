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

test('AC-CHECKOUT-03 - Process Payment @smoke', async ({ page }) => {
    // Goto Cart then Checkout
    const cart = new CartPage(page);
    await cart.goto();
    await cart.checkout();

    // Fill in Customer details
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

    // Proceed to Payment and confirm details
    const orderSummary = checkout.orderSummary();
    await orderSummary.acceptOrder();

    // Should be on CheckoutPayment page
    // Input Payment details and continue
    const checkoutPayment = new CheckoutPaymentPage(page);
    const stripePaymentForm = checkoutPayment.stripePaymentForm();

    await stripePaymentForm.fillCard({
        number: '4242424242424242',
        expiry: '1234',
        cvc: '123',
        zip: '12345',
    });

    await stripePaymentForm.submitPayment();

    // Assert
    await expect(page).toHaveURL(/checkoutsuccess/);
    const checkoutSuccess = new CheckoutSuccessPage(page);

    await checkoutSuccess.expectPaymentSuccessMsg();
    await expect(checkoutSuccess.continueShoppingBtn).toBeVisible();
    await expect(checkoutSuccess.viewOrdersBtn).toBeVisible();

    // Assert Cart is empty after successful payment
    await cart.goto();
    await cart.expectEmptyCartMsg();
});

test('Enter Payment without filling in Customer Details not allow', async ({ page }) => {
    // Goto Cart then Checkout
    const cart = new CartPage(page);
    await cart.goto();
    await cart.checkout();

    // Ignore Customer inputs
    // Check for error message
    await expect(page).toHaveURL(/cart/);
    const checkout = new CheckoutDetailsPage(page);
    const orderSummary = checkout.orderSummary();
    await orderSummary.acceptOrder();

    const customerForm = checkout.customerForm();
    const nameInput = customerForm.firstnameInput;
    const isMissing = await nameInput.evaluate(
        (el) => el.validity.valueMissing
    );
    expect(isMissing).toBe(true);
    
    await expect(page).toHaveURL(/checkoutdetails/);
    await expect(page).not.toHaveURL(/checkoutpayment/);
});