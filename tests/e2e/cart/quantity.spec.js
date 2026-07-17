import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';

test.beforeEach(async ({ page, context }) => {
  // First add an item to Cart
  const shop = new ShopPage(page);
  await shop.goto();
  await shop.product(1).addToCart();
});

test('TC-CART-003 update quantity of an Item in Cart', async ({ page }) => {
  // Go to Cart and update quantity of an item
  const cart = new CartPage(page);
  await cart.goto();

  // Initial
  const cartItem = cart.item(1);
  await expect(cartItem.name).toHaveText('Ethiopian Sunrise');
  await expect(cartItem.quantity).toHaveValue('1');

  // Increasing
  await cartItem.increase();
  await cartItem.expectQuantity(2);

  await cartItem.increase();
  await cartItem.expectQuantity(3);

  await cartItem.increaseQuantity(5);
  await cartItem.expectQuantity(5);
});

test('TC-CART-004 Decrease Product Quantity to Zero (shop)', async ({ page }) => {
  // Add Item from Shop page
  const shop = new ShopPage(page);
  await shop.goto();
  const product = shop.product(1);

  // Press "-" button
  await product.decrease();

  // Check that it can't go to 0 and the "Add" button is back
  await expect(product.addBtn).toBeVisible();
});

test('TC-CART-004 Decrease Product Quantity to Zero (cart)', async ({ page }) => {
  const cart = new CartPage(page);
  await cart.goto();

  // Initial assertions
  const cartItem = cart.item(1);

  await expect(cartItem.name).toHaveText('Ethiopian Sunrise');
  await expect(cartItem.quantity).toHaveValue('1');

  // Assert item is removed
  await cartItem.decrease();
  await expect(cartItem.root).toHaveCount(0);
});

test('TC-CART-005 Cart Total Updates After Quantity Change', async ({ page }) => {
  // Add a second Item
  const shop = new ShopPage(page);
  await shop.goto();
  await shop.product(2).addToCart();

  const cart = new CartPage(page);
  await cart.goto();
  
  // Initial
  const cartItem1 = cart.item(1);
  const cartItem2 = cart.item(2);
  await expect(cartItem1.name).toHaveText('Ethiopian Sunrise');
  await cartItem1.expectTotal(18.00);
  await expect(cartItem2.name).toHaveText('Colombian Supremo');
  await cartItem2.expectTotal(16.00);

  // Increasing
  await cartItem1.increase();
  await cartItem1.expectTotal(36.00);

  await cartItem2.increase();
  await cartItem2.expectTotal(32.00);

  await cartItem1.increaseQuantity(5);
  await cartItem1.expectTotal(90.00);
});