import { test, expect } from '@playwright/test';

test('AC-CHECKOUT-01 - Get Summary of Order', async ({ page }) => {
    // Add items to cart
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

    // Goto Cart and Checkout
    await page.goto('/cart');
    await page.getByRole('button', { name: 'Proceed to Checkout' }).click();

    // Assert items are visible at checkout summary
    await expect(
        page.getByRole('heading', { name: '1 × Colombian Supremo' })
    ).toBeVisible();

    await expect(
        page.getByRole('heading', { name: '1 × Stoneware Latte Mug' })
    ).toBeVisible();
});