import { expect } from '@playwright/test';
import { CartItem } from './components/cart/CartItem';

export class CartPage {
    constructor(page) {
        this.page = page;

        this.items = page.getByTestId('cart-item');

        this.checkoutButton = page.getByTestId('checkout-btn');
        this.continueShoppingButton = page.getByTestId('continue-shopping-btn');
        this.total = page.getByTestId('cart-total');
        this.emptyMessage = page.getByTestId('empty-cart-txt');
    }

    item(index) {
        return new CartItem(
            this.items.nth(index)
        );
    }

    itemByName(name) {
        return new CartItem(
            this.items.filter({
                has: this.page.getByTestId('cart-item-name').filter({
                    hasText: name,
                }),
            })
        );
    }

    async itemCount() {
        return await this.items.count();
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