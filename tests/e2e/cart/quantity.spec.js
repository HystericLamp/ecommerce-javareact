import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';

test.beforeEach(async ({ page, context }) => {
  // First add an item to Cart
  const shop = new ShopPage(page);
  await shop.goto();

  await shop.product(0).addToCart();
});

test('TC-CART-003 update quantity of an Item in Cart', async ({ page }) => {
  // Go to Cart and update quantity of an item
  const cart = new CartPage(page);
  await cart.goto();

  // Initial
  const cartItem = cart.item(0);
  await expect(cartItem.name).toHaveText('Ethiopian Sunrise');
  await cartItem.expectQuantity(1);

  // Increasing
  await cartItem.increase();
  await cartItem.expectQuantity(2);

  await cartItem.increase();
  await cartItem.expectQuantity(3);

  await cartItem.setQuantity(5);
  await cartItem.expectQuantity(5);
});

test('TC-CART-004 Decrease Product Quantity to Zero (shop)', async ({ page }) => {
  // Add Item from Shop page
  const shop = new ShopPage(page);
  await shop.goto();
  const product = shop.product(0);

  // Press "-" button
  await product.decrease();

  // Check that it can't go to 0 and the "Add" button is back
  await expect(product.addButton).toBeVisible();
});

test('TC-CART-004 Decrease Product Quantity to Zero (cart)', async ({ page }) => {
  const cart = new CartPage(page);
  await cart.goto();

  // Initial assertions
  const cartItem = cart.item(0);

  await expect(cartItem.name).toHaveText('Ethiopian Sunrise');
  await cartItem.expectQuantity(1);

  // Assert item is removed
  await cartItem.decrease();
  await expect(cartItem.root).toHaveCount(0);
});

test('TC-CART-005 Cart Total Updates After Quantity Change', async ({ page }) => {
  // Add a second Item
  const shop = new ShopPage(page);
  await shop.goto();
  await shop.product(1).addToCart();

  const cart = new CartPage(page);
  await cart.goto();
  
  // Initial
  const cartItem1 = cart.item(0);
  const cartItem2 = cart.item(1);
  await expect(cartItem1.name).toHaveText('Ethiopian Sunrise');
  await cartItem1.expectTotal(18.00);
  await expect(cartItem2.name).toHaveText('Colombian Supremo');
  await cartItem2.expectTotal(16.00);

  // Increasing
  await cartItem1.increase();
  await cartItem1.expectTotal(36.00);

  await cartItem2.increase();
  await cartItem2.expectTotal(32.00);

  await cartItem1.setQuantity(5);
  await cartItem1.expectTotal(90.00);
});