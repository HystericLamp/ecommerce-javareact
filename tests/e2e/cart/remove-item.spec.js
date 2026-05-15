import { test, expect } from '@playwright/test';

test('AC-CART-07 remove item from cart', async ({ page }) => {
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

  // Check initial state in cart
  await page.goto('/cart');

  await expect(page.getByText('Your Cart')).toBeVisible();
  
  const item = page.locator('.flex.items-center.p-4').filter({
    hasText: 'Colombian Supremo',
  });

  await expect(item).toBeVisible();
  await expect(item.locator('span.font-medium')).toHaveText('1');
  await expect(page.getByText('Colombian Supremo')).toBeVisible();

  // Press remove/trashcan button and assert
  await item.locator('button').last().click();
  
  await expect(item).not.toBeVisible();
  await expect(page.getByText('Colombian Supremo')).not.toBeVisible();
});