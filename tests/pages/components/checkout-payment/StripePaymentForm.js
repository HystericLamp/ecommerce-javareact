import { test, expect } from '@playwright/test';

export class StripePaymentForm {
    constructor(page) {
        this.page = page;

        this.root = page.getByTestId('stripe-payment-form-card-div');

        const frame = this.root.locator('iframe').first().contentFrame();

        this.cardNumberInput = frame.getByPlaceholder('Card number');
        this.dateInput = frame.getByPlaceholder('MM / YY');
        this.cvcInput = frame.getByPlaceholder('CVC');
        this.zipInput = frame.getByPlaceholder('ZIP');

        this.submitBtn = page.getByTestId('payment-submit');
    }

    async fillCard({number, expiry, cvc, zip}) {
        await expect(this.cardNumberInput).toBeVisible();

        await this.cardNumberInput.waitFor();
        await this.cardNumberInput.fill(String(number));

        await this.dateInput.waitFor();
        await this.dateInput.fill(String(expiry));

        await this.cvcInput.waitFor();
        await this.cvcInput.fill(String(cvc));

        if (zip) {
            await this.zipInput.waitFor();
            await this.zipInput.fill(String(zip));
        }
    }

    async submitPayment() {
        await this.submitBtn.click();
    }
}