import { test, expect } from '@playwright/test';
import { ShopPage } from '../../pages/ShopPage';

test('AC-PRODUCT-MENU-01 - Get All Product Information @smoke', async ({ page }) => {
  const shop = new ShopPage(page);
  await shop.goto();

  // Main page heading
  await expect(shop.heading).toBeVisible();

  // Category headings
  await expect(shop.category('Coffee').header).toBeVisible();
  await expect(shop.category('Brew Gear').header).toBeVisible();
  await expect(shop.category('Accessories').header).toBeVisible();
  await expect(shop.category('Snacks').header).toBeVisible();

  // Product assertions
  await expect(shop.product('ethiopian-sunrise', 'Ethiopian Sunrise').name).toBeVisible();
  await expect(shop.product('classic-french-press', 'Classic French Press').name).toBeVisible();
  await expect(shop.product('stoneware-latte-mug', 'Stoneware Latte Mug').name).toBeVisible();
  await expect(shop.product('chocolate-dipped-biscotti', 'Chocolate-Dipped Biscotti').name).toBeVisible();
});