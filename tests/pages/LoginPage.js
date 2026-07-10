import { expect } from '@playwright/test';

export class LoginPage {
    constructor(page) {
        this.page = page;

        this.emailInput = page.getByTestId('login-email-input');
        this.passwordInput = page.getByTestId('login-password-input');
        this.loginBtn = page.getByTestId('login-btn');
        this.signupLink = page.getByTestId('sign-up-link');
    }

    async goto() {
        await this.page.goto('/login');
    }

    async setEmail(value) {
        await this.emailInput.fill(String(value));
    }

    async setPassword(value) {
        await this.passwordInput.fill(String(value));
    }

    async login() {
        await this.loginBtn.click();
    }

    async signupGoto() {
        await this.signupLink.click();
    }
}