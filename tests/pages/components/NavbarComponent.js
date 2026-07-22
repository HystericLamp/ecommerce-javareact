import { expect } from '@playwright/test';

export class NavbarComponent {
    constructor(page) {
        this.page = page;

        this.root = page.getByTestId('navbar-root');

        this.defaultLink = this.root.getByTestId('default-link');
        this.homeLink = this.root.getByTestId('home-link');
        this.shopLink = this.root.getByTestId('shop-link');
        this.cartBtn = this.root.getByTestId('cart-btn');
        this.loggedInEmail = this.root.getByTestId('authenticated-email');
        this.logoutBtn = this.root.getByTestId('logout-btn');
        this.loginGotoBtn = this.root.getByTestId('login-goto-btn');
    }

    async expectAuthEmail(email) {
        await expect(this.loggedInEmail).toHaveText(String(email));
    }

    async clickDefaultLink() {
        await this.defaultLink.click();
    }

    async clickHomeLink() {
        await this.homeLink.click();
    }

    async clickShopLink() {
        await this.shopLink.click();
    }

    async clickCartBtn() {
        await this.cartBtn.click();
    }

    async logout() {
        await this.logoutBtn.click();
    }

    async loginGoto() {
        await this.loginGotoBtn.click();
    }
}