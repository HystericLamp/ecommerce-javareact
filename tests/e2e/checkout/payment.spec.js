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
});

test('Enter Payment without filling in Customer Details', async ({ page }) => {
    // Goto Cart then Checkout
    await page.goto('/cart');
    await page.getByRole('button', { name: 'Proceed to Checkout' }).click();

    // Ignore Customer inputs
    // Proceed to Payment and confirm details
});

test('AC-CHECKOUT-04 - Failed Payment', async ({ page }) => {

});

test('AC-CHECKOUT-05 - Retry Payment', async ({ page }) => {

});