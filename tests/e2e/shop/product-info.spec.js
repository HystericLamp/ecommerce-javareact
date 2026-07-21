import { test, expect } from '@playwright/test';
import { ShopPage } from '../../pages/ShopPage';

test('TC-PROD-001 - View Product List @smoke', async ({ page }) => {
  const shop = new ShopPage(page);
  await shop.goto();

  const count = await shop.productCount();
  expect(count).toBeGreaterThan(0);

  const firstProduct = shop.product(0);
  await firstProduct.expectVisible();
});

test('TC-PROD-002 - Verify Product Details @smoke', async ({ page }) => {
  const shop = new ShopPage(page);
  await shop.goto();

  const count = await shop.productCount();
  expect(count).toBeGreaterThan(0);

  for (let i = 0; i < count; i++) {
    const product = shop.product(i);
    await product.expectDetailsVisible();
  }
});