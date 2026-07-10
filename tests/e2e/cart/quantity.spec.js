import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';

test('AC-CART-05 update quantity of an Item in Cart', async ({ page }) => {
  // First add an item to Cart
  const shop = new ShopPage(page);
  await shop.goto();
  await shop.product(2).addToCart();

  // Go to Cart and update quantity of an item
  const cart = new CartPage(page);
  await cart.goto();

  // Initial
  const cartItem = cart.item(2);
  await expect(cartItem.name).toHaveText('Colombian Supremo');
  await expect(cartItem.quantity).toHaveValue('1');

  // Increasing
  await cartItem.increase();
  await expect(cartItem.quantity).toHaveValue('2');

  await cartItem.increase();
  await expect(cartItem.quantity).toHaveValue('3');
});

test('AC-CART-06 do not update an item quantity below 1 (item removed)', async ({ page }) => {
  // Go to Shop and add an Item
  const shop = new ShopPage(page);
  await shop.goto();
  await shop.product(1).addToCart();

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