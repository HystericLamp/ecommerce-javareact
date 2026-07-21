import { test, expect } from '@playwright/test';

export class OrderItems {
    constructor(locator) {
        this.root = locator;

        this.increaseBtn = locator.getByTestId('increase-button');
        this.decreaseBtn = locator.getByTestId('decrease-button');
        this.removeBtn = locator.getByTestId('remove-button');

        this.quantity = locator.getByTestId('checkout-item-quantity');
        this.orderItemTotal = locator.getByTestId(`checkout-item-cost`);
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

    async setQuantity(quantity) {
        await this.quantity.fill(String(quantity));
        await this.quantity.blur();
    }

    async expectQuantity(value) {
        await expect(this.quantity).toHaveValue(String(value));
    }

    async expectItemTotal(amount) {
        await expect(this.orderItemTotal).toHaveText(amount);
    }

    async expectVisible() {
        await expect(this.root).toBeVisible();
    }

    async expectHidden() {
        await expect(this.root).toBeHidden();
    }

    async expectNotVisible() {
        await expect(this.root).not.toBeVisible();
    }
}