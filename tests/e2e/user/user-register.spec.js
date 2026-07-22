import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { RegisterPage } from '../../pages/RegisterPage';
import { deleteTestUser } from './helpers/testUser';

test.describe.serial('Register User flow', () => {
  let user;

  const email = `user-${Date.now()}-${Math.random()}@email.com`;
  const password = "Password123!";
  const apiBaseURL = process.env.API_BASE_URL;

  test.afterAll(async ({ request }) => {
    await deleteTestUser(request, apiBaseURL, email);
  });

  test('TC-AUTH-001 Successful User Registration @smoke', async ({ page }) => {
    // Go to register a new user
    const login = new LoginPage(page);
    await login.goto();
    await login.signupGoto();

    // Input new user details
    const register = new RegisterPage(page);
    await register.setName("John Doe");
    await register.setEmail(email);
    await register.setPassword(password);
    await register.setConfirmPassword(password);

    // Submit
    const dialogPromise = page.waitForEvent('dialog');

    await register.confirmSubmit();

    const dialog = await dialogPromise;

    expect(dialog.message()).toBe('New Member created!');
    await dialog.accept();
  });
});