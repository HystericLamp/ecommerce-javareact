import { expect } from '@playwright/test';

export class CartItem {
  constructor(locator) {
    this.root = locator;

    this.name = locator.getByTestId('cart-item-name');
    this.quantity = locator.getByTestId('cart-item-quantity');
    this.total = locator.getByTestId('cart-item-total');

    this.increaseButton = locator.getByTestId('increase-button');
    this.decreaseButton = locator.getByTestId('decrease-button');
    this.removeButton = locator.getByTestId('remove-button');
  }

  async increase() {
    await this.increaseButton.click();
  }

  async setQuantity(quantity) {
    await this.quantity.fill(String(quantity));
    await this.quantity.blur();
  }

  async decrease() {
    await this.decreaseButton.click();
  }

  async remove() {
    await this.removeButton.click();
  }

  async expectQuantity(quantity) {
    await expect(this.quantity).toHaveValue(String(quantity));
  }

  async expectTotal(total) {
    await expect(this.total).toHaveText(`$${total.toFixed(2)}`);
  }
}