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

test('AC-CHECKOUT-02 - Update Order', async ({ page }) => {
    // Goto Cart and Checkout
    await page.goto('/cart');
    await page.getByRole('button', { name: 'Proceed to Checkout' }).click();

    // Assert initial state
    // Initial quantity state is 1 for each item
    const colombianSupremoCard = page.locator('.rounded-xl').filter({
        hasText: 'Colombian Supremo'
    });

    await expect(colombianSupremoCard).toContainText('$16.00');

    const latteMugCard = page.locator('.rounded-xl').filter({
        hasText: 'Stoneware Latte Mug'
    });

    await expect(latteMugCard).toContainText('$22.00');

    // Update items
    const quantityInput1 = colombianSupremoCard.locator('input[type="number"]');

    await quantityInput1.fill('3');
    await quantityInput1.press('Enter');

    const quantityInput2 = latteMugCard.locator('input[type="number"]');

    await quantityInput2.fill('2');
    await quantityInput2.press('Enter');

    // Assert change
    await expect(colombianSupremoCard).toContainText('$48.00');

    await expect(latteMugCard).toContainText('$44.00');
});

test('Update Order not allow 0 or negative quantity', async ({ page }) => {

});