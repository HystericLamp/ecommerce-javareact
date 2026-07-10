import { test, expect } from '@playwright/test';

export class OrderItems {
    constructor(page, id) {
        this.page = page;
        this.id = id;

        this.root = page.getByTestId(`order-item-${id}`);

        this.increaseBtn = this.root.getByTestId(`increase-${id}`);
        this.decreaseBtn = this.root.getByTestId(`decrease-${id}`);
        this.removeBtn = this.root.getByTestId(`remove-${id}`);
        this.quantity = this.root.getByTestId(`quantity-${id}`);
        this.orderItemTotal = this.root.getByTestId(`order-item-cost-${id}`);
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

    async setQuantity(value) {
        await this.quantity.fill(String(value));
        await this.quantity.press('Enter');
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