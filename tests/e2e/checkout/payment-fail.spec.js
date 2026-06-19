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

test('AC-CHECKOUT-04 - Failed Payment', async ({ page }) => {
    // Go to checkout page
    await page.goto('/cart');
    await page.getByRole('button', { name: 'Proceed to Checkout' }).click();

    // Fill in Demo details and continue to payment page
    const fillDemoButton = page.getByRole('button', {
        name: 'Use Demo Information'
    });

    await fillDemoButton.click();

    const continueButton = page.getByRole('button', {
        name: 'Continue to Payment'
    });

    await expect(continueButton).toBeEnabled();
    await continueButton.click();

    // payment processor returns failure
    const cardFrame = page.frameLocator('iframe[title*="Secure card payment input"]');

    await cardFrame.getByPlaceholder('Card number').fill('4000000000009995');
    await cardFrame.getByPlaceholder('MM / YY').fill('1234');
    await cardFrame.getByPlaceholder('CVC').fill('123');
    await cardFrame.getByPlaceholder('ZIP').fill('12345');

    await page.getByRole('button', {
        name: 'Complete Purchase'
    }).click();

    await expect(
        page.getByTestId('payment-error')
    ).toContainText('Your card has insufficient funds. Try a different card.');

    await expect(page).toHaveURL(/checkoutpayment/);

    // Go to cart to check cart is not empty
    await page.goto('/cart');

    await expect(page.getByText('Your Cart')).toBeVisible();
    const item = page.locator('.flex.items-center.p-4').first();
    await expect(item).toBeVisible();
    await expect(page.getByText('Colombian Supremo')).toBeVisible();
});