import { expect } from '@playwright/test';

export class CartItem {
  constructor(page, id) {
    this.page = page;
    this.id = id;

    this.root = page.getByTestId(`cart-item-${id}`);

    this.name = this.root.getByTestId(`name-${id}`);
    this.increaseBtn = this.root.getByTestId(`increase-${id}`);
    this.decreaseBtn = this.root.getByTestId(`decrease-${id}`);
    this.removeBtn = this.root.getByTestId(`remove-${id}`);
    this.total = this.root.getByTestId(`item-total-${id}`);
    this.quantity = this.root.getByTestId(`quantity-${id}`);
  }

  async increase() {
    await this.increaseBtn.click();
  }

  async decrease() {
    await this.decreaseBtn.click();
  }

  async remove() {
    await this.removeBtn.click();
  }

  async expectTotal(amount) {
    await expect(this.total).toHaveText(amount);
  }
}