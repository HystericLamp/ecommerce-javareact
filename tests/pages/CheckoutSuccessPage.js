import { expect } from '@playwright/test';

export class CheckoutSuccessPage {
    constructor(page) {
        this.page = page;

        this.paymentSuccess = page.getByTestId('payment-success-header');
        this.continueShoppingBtn = page.getByTestId('continue-shopping-btn');
        this.viewOrdersBtn = page.getByTestId('view-orders-btn');
    }

    async expectPaymentSuccessMsg() {
        await expect(this.paymentSuccess).toBeVisible();
    }

    async continueShopping() {
        await this.continueShoppingButton.click();
    }

    async continueShopping() {
        await this.viewOrdersBtn.click();
    }
}