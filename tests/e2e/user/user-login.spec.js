import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { RegisterPage } from '../../pages/RegisterPage';
import { NavbarComponent } from '../../pages/components/NavbarComponent';

test.describe.serial('User flow', () => {
  const email = `doe-${Date.now()}-${Math.random()}@email.com`;
  const password = 'Lachesis123';

  const apiBaseURL = process.env.API_BASE_URL;

  test.afterAll(async ({ request }) => {
    // Delete new users created
    await request.delete(
      `${apiBaseURL}/api/users/deleteUser/byEmail`,
      {
        params: {
          email: email,
        },
      }
    );
  });

  test('AC-USER-01 register new member @smoke', async ({ page }) => {
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

  test('AC-USER-02 login as an existing user @smoke', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    
    await loginPage.setEmail(email);
    await loginPage.setPassword(password);

    const dialogPromise = page.waitForEvent('dialog');

    await loginPage.login();

    const dialog = await dialogPromise;

    expect(dialog.message()).toBe('Logged in!');
    await dialog.accept();

    const navbar = new NavbarComponent(page);
    await navbar.expectAuthEmail();
  });

  test('logout test', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();

    await loginPage.setEmail(email);
    await loginPage.setPassword(password);

    const dialogPromise = page.waitForEvent('dialog');

    await loginPage.login();

    const dialog = await dialogPromise;
    await dialog.accept();

    const navbar = new NavbarComponent(page);
    await navbar.logout();

    await expect(navbar.loginGotoBtn).toBeVisible();
    await expect(navbar.logoutBtn).not.toBeVisible();
    await expect(navbar.loggedInEmail).not.toBeVisible();
  });
});