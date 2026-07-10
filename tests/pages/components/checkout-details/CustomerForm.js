import { expect } from '@playwright/test';

export class CustomerForm {
    constructor(page) {
        this.page = page;

        this.root = page.getByTestId('customer-form-root');

        this.firstnameInput = this.root.getByTestId('form-firstname');
        this.lastnameInput = this.root.getByTestId('form-lastname');
        this.emailInput = this.root.getByTestId('form-email');
        this.address1Input = this.root.getByTestId('form-address1');
        this.address2Input = this.root.getByTestId('form-address2');
        this.cityInput = this.root.getByTestId('form-city');
        this.provinceInput = this.root.getByTestId('form-province');
        this.podtalcodeInput = this.root.getByTestId('form-postalcode');
        this.countryInput = this.root.getByTestId('form-country');
    }

    async setFirstname(value) {
        await this.firstnameInput.fill(String(value));
    }

    async setLastname(value) {
        await this.lastnameInput.fill(String(value));
    }

    async setEmail(value) {
        await this.emailInput.fill(String(value));
    }

    async setAddress1(value) {
        await this.address1Input.fill(String(value));
    }

    async setAddress2(value) {
        await this.address2Input.fill(String(value));
    }

    async setCity(value) {
        await this.cityInput.fill(String(value));
    }

    async setProvince(value) {
        await this.provinceInput.fill(String(value));
    }

    async setPostalcode(value) {
        await this.podtalcodeInput.fill(String(value));
    }

    async setCountry(value) {
        await this.countryInput.fill(String(value));
    }
}