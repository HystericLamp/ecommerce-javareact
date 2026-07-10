export class RegisterPage {
    constructor(page) {
        this.page = page;

        this.nameInput = page.getByTestId('name-input');
        this.emailInput = page.getByTestId('email-input');
        this.passwordInput = page.getByTestId('password-input');
        this.confirmPasswordInput = page.getByTestId('confirm-password-input');
        this.submitBtn = page.getByTestId('submit-btn');
    }

    async setName(value) {
        await this.nameInput.fill(String(value));
    }

    async setEmail(value) {
        await this.emailInput.fill(String(value));
    }

    async setPassword(value) {
        await this.passwordInput.fill(String(value));
    }

    async setConfirmPassword(value) {
        await this.confirmPasswordInput.fill(String(value));
    }

    async confirmSubmit() {
        await this.submitBtn.click();
    }
}