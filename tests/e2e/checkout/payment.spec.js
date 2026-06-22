import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
    // Add items to Cart
    await page.goto('/shop');

    const item1 = page.locator('.p-4').filter({
        has: page.getByRole('heading', {
        name: 'Colombian Supremo',
        }),
    });

    await item1
        .getByRole('button', { name: 'Add' })
        .click();

    const item2 = page.locator('.p-4').filter({
        has: page.getByRole('heading', {
        name: 'Stoneware Latte Mug',
        }),
    });

    await item2
        .getByRole('button', { name: 'Add' })
        .click();
});

test('AC-CHECKOUT-03 - Process Payment', async ({ page }) => {
    // Goto Cart then Checkout
    await page.goto('/cart');
    await page.getByRole('button', { name: 'Proceed to Checkout' }).click();

    // Fill in Customer details
    await page.locator('input[name="firstName"]').fill('First');
    await page.locator('input[name="lastName"]').fill('Last');
    await page.locator('input[name="email"]').fill('customer@example.com');
    await page.locator('input[name="addressLine1"]').fill('123 Main St');
    await page.locator('input[name="city"]').fill('City');
    await page.locator('input[name="provinceState"]').fill('State');
    await page.locator('input[name="postalCode"]').fill('T2P 1J9');
    await page.locator('input[name="country"]').fill('Country');

    // Proceed to Payment and confirm details
    const continueButton = page.getByRole('button', {
        name: 'Continue to Payment'
    });

    await expect(continueButton).toBeEnabled();
    await continueButton.click();

    // Should be on CheckoutPayment page
    // Input Payment details and continue
    const cardFrame = page.frameLocator('iframe[title*="Secure card payment input"]');

    await cardFrame.getByPlaceholder('Card number').fill('4242424242424242');
    await cardFrame.getByPlaceholder('MM / YY').fill('1234');
    await cardFrame.getByPlaceholder('CVC').fill('123');
    await cardFrame.getByPlaceholder('ZIP').fill('12345');

    await page.getByRole('button', { name: 'Complete Purchase' }).click();

    // Assert
    await expect(page).toHaveURL('/checkoutsuccess');

    await expect(
        page.getByRole('heading', { name: 'Payment Successful' })
    ).toBeVisible();

    await expect(
        page.getByRole('button', { name: 'Continue Shopping' })
    ).toBeVisible();

    await expect(
        page.getByRole('button', { name: 'View Orders' })
    ).toBeVisible();

    // Assert Cart is empty after successful payment
    await page.goto('/cart');

    await expect(
        page.getByText('Your cart is empty')
    ).toBeVisible();
});

test('Enter Payment without filling in Customer Details not allow', async ({ page }) => {
    // Goto Cart then Checkout
    await page.goto('/cart');
    await page.getByRole('button', { name: 'Proceed to Checkout' }).click();

    // Ignore Customer inputs
    // Check for error message
    const continueButton = page.getByRole('button', {
        name: 'Continue to Payment'
    });

    await expect(continueButton).toBeEnabled();
    await continueButton.click();

    const nameInput = page.getByPlaceholder("First Name");
    const isMissing = await nameInput.evaluate(
        (el) => el.validity.valueMissing
    );
    expect(isMissing).toBe(true);
    
    await expect(page).toHaveURL(/checkoutdetails/);
    await expect(page).not.toHaveURL(/checkoutpayment/);
});