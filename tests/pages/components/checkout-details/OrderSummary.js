import { test, expect } from '@playwright/test';

export class OrderSummary {
    constructor(page) {
        this.page = page;

        this.root = page.getByTestId('order-summary-root');

        this.itemCount = this.root.getByTestId('item-count');
        this.orderTotal = this.root.getByTestId('order-total');
        this.submitBtn = this.root.getByTestId('checkout-submit');
        this.backToCartBtn = this.root.getByTestId('checkout-back-to-cart');
    }

    async expectItemCount(amount) {
        await expect(this.itemCount).toHaveText(amount);
    }

    async expectOrderTotal(amount) {
        await expect(this.orderTotal).toHaveText(amount);
    }

    async acceptOrder() {
        await this.submitBtn.click();
    }

    async backToCart() {
        await this.backToCartBtn.click();
    }
}