import { expect } from '@playwright/test';
import { CartItem } from './components/cart/CartItem';

export class CartPage {
    constructor(page) {
        this.page = page;

        this.checkoutButton = page.getByTestId('checkout-btn');
        this.continueShoppingButton = page.getByTestId('continue-shopping-btn');
        this.total = page.getByTestId('cart-total');
        this.emptyMessage = page.getByTestId('empty-cart-txt');
    }

    item(id) {
        return new CartItem(this.page, id);
    }

    async goto() {
        await this.page.goto('/cart', {
            waitUntil: 'domcontentloaded',
        });
    }

    async checkout() {
        this.checkoutButton.click();
    }

    async continueShopping() {
        await this.continueShoppingButton.click();
    }

    async expectEmptyCartMsg() {
        await expect(this.emptyMessage).toBeVisible();
    }

    async expectTotal(total) {
        await expect(this.total).toHaveText(`$${total.toFixed(2)}`);
    }
}