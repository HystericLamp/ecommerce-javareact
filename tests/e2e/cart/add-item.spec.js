import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';

test.beforeEach(async ({ page }) => {
  await page.goto('/');
  await page.evaluate(() => {
    localStorage.clear();
    sessionStorage.clear();
  });
});

test('AC-CART-01 user can add item to cart @smoke', async ({ page }) => {
  const shop = new ShopPage(page);
  await shop.goto();
  const product = shop.product(2);
  await product.addToCart();

  const cart = new CartPage(page);
  await cart.goto();
  
  const cartItem = cart.item(2);
  await expect(cartItem.name).toHaveText('Colombian Supremo');
  await expect(cartItem.quantity).toHaveValue('1');
});

test('AC-CART-03 decreasing quantity from 1 removes the item', async ({ page }) => {
  // Add Item from Shop page
  const shop = new ShopPage(page);
  await shop.goto();
  const product = shop.product(2);
  await product.addToCart();

  // Press "-" button
  await product.decrease();

  // Check that it can't go to 0 and the "Add" button is back
  await expect(product.addBtn).toBeVisible();
});

test('AC-CART-04 adding existing Item into the Cart should increment quantity', async ({ page }) => {
  const shop = new ShopPage(page);
  await shop.goto();
  const product = shop.product(1);

  // Check initial "Add" state
  await expect(product.addBtn).toBeVisible();
  await product.addToCart();

  // Check after "add" state
  await expect(product.quantity).toHaveText(/1\s+in cart/);
  await expect(product.increaseBtn).toBeVisible();
  await expect(product.decreaseBtn).toBeVisible();

  // Pressing "+" button
  await product.increase();
  await expect(product.quantity).toHaveText(/2\s+in cart/);;
});