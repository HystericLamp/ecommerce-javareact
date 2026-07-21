import { expect } from '@playwright/test';

export class ProductCard {
    constructor(locator) {
        this.card = locator;

        this.name = locator.getByTestId('product-name');
        this.price = locator.getByTestId('product-price');

        this.addButton = locator.getByTestId('add-button');

        this.quantity = locator.getByTestId('quantity');
        this.increaseButton = locator.getByTestId('increase-button');
        this.decreaseButton = locator.getByTestId('decrease-button');
    }

    async addToCart() {
        await this.addButton.click();
    }

    async increase() {
        await this.increaseButton.click();
    }

    async decrease() {
        await this.decreaseButton.click();
    }

    async getName() {
        return await this.name.textContent();
    }

    async expectVisible() {
        await expect(this.card).toBeVisible();
    }


    async expectDetailsVisible() {
        await expect(this.name).toBeVisible();
        await expect(this.price).toBeVisible();
    }
}