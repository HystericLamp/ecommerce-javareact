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

test('TC-CART-001 Add Product to Cart @smoke', async ({ page }) => {
  const shop = new ShopPage(page);
  await shop.goto();
  const product = shop.productByName('Colombian Supremo');
  await product.addToCart();

  const cart = new CartPage(page);
  await cart.goto();
  
  const cartItem = cart.itemByName('Colombian Supremo');
  await expect(cartItem.name).toHaveText('Colombian Supremo');
  await cartItem.expectQuantity(1);
});

test('TC-CART-002 Add Multiple Products to Cart', async ({ page }) => {
  // Add items
  const shop = new ShopPage(page);
  await shop.goto();

  const product1 = shop.product(0);
  await product1.addToCart();

  const product2 = shop.product(10);
  await product2.addToCart();

  // Check cart
  const cart = new CartPage(page);
  await cart.goto();
  
  const cartItem1 = cart.item(0);
  await expect(cartItem1.name).toHaveText('Ethiopian Sunrise');
  await cartItem1.expectQuantity(1);

  const cartItem2 = cart.item(1);
  await expect(cartItem2.name).toHaveText('Stoneware Latte Mug');
  await cartItem1.expectQuantity(1);
});