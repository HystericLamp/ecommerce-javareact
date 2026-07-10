import { test, expect } from '@playwright/test';
import { StripePaymentForm } from './components/checkout-payment/StripePaymentForm';

export class CheckoutPaymentPage {
    constructor(page) {
        this.page = page;
    }

    stripePaymentForm() {
        return new StripePaymentForm(this.page);
    }
}