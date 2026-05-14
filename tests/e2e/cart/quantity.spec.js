import { test, expect } from '@playwright/test';

// test.beforeEach(async ({ page }) => {
//   await page.addInitScript(() => {
//     localStorage.clear();
//     sessionStorage.clear();
//   });
// });

test('AC-CART-05 update quantity of an Item in Cart', async ({ page }) => {
    // First add an item to Cart
    await page.goto('/shop');

    const productCard = page.locator('.p-4').filter({
        has: page.getByRole('heading', {
        name: 'Colombian Supremo',
        }),
    });

  await productCard
    .getByRole('button', { name: 'Add' })
    .click();

  // Go to Cart and update quantity of an item
  await page.goto('/cart');

  await expect(page.getByText('Your Cart')).toBeVisible();

  const item = page.locator('.flex.items-center.p-4').first();
  await expect(item).toBeVisible();

  await item.getByRole('button', { name: '+' }).click();
  await expect(item.locator('span.font-medium')).toHaveText('2');

  await page.getByRole('button', { name: '+' }).click();
  await expect(item.locator('span.font-medium')).toHaveText('3');
});