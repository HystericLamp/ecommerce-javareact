import { test, expect } from '@playwright/test';
import { CartPage } from '../../pages/CartPage';
import { ShopPage } from '../../pages/ShopPage';

test('TC-CART-006 Remove Product from Cart', async ({ page }) => {
  // First add an item to Cart
  const shop = new ShopPage(page);
  await shop.goto();
  await shop.product(2).addToCart();

  // Check initial state in cart
  const cart = new CartPage(page);
  await cart.goto();
  const cartItem = cart.item(2);
  
  await expect(cartItem.name).toHaveText('Colombian Supremo');
  await expect(cartItem.quantity).toHaveValue('1');

  // Press remove/trashcan button and assert
  await cartItem.remove();
  
  await expect(cartItem.name).not.toBeVisible();
});