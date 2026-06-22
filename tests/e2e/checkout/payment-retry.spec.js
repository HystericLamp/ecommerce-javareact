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

test('AC-CHECKOUT-05 - Retry Payment', async ({ page }) => {
    // First make Payment fail
    await page.goto('/cart');
    await page.getByRole('button', { name: 'Proceed to Checkout' }).click();

    const fillDemoButton = page.getByRole('button', {
        name: 'Use Demo Information'
    });

    await fillDemoButton.click();

    const continueButton = page.getByRole('button', {
        name: 'Continue to Payment'
    });

    await expect(continueButton).toBeEnabled();
    await continueButton.click();

    const cardFrame = page.frameLocator('iframe[title*="Secure card payment input"]');

    await cardFrame.getByPlaceholder('Card number').fill('4000000000009995');
    await cardFrame.getByPlaceholder('MM / YY').fill('1234');
    await cardFrame.getByPlaceholder('CVC').fill('123');
    await cardFrame.getByPlaceholder('ZIP').fill('12345');

    await page.getByRole('button', { name: 'Complete Purchase' }).click();

    await expect(
        page.getByTestId('payment-error')
    ).toContainText('Your card has insufficient funds. Try a different card.');

    await expect(page).toHaveURL(/checkoutpayment/);

    // Retry payment is successful
    await cardFrame.getByPlaceholder('Card number').fill('4242424242424242');
    await cardFrame.getByPlaceholder('MM / YY').fill('1234');
    await cardFrame.getByPlaceholder('CVC').fill('123');
    await cardFrame.getByPlaceholder('ZIP').fill('12345');

    await page.getByRole('button', { name: 'Complete Purchase' }).click();
    
    // Assert success
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
});