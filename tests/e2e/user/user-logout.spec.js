import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { RegisterPage } from '../../pages/RegisterPage';
import { NavbarComponent } from '../../pages/components/NavbarComponent';
import { createTestUser, deleteTestUser } from './helpers/testUser';

test.describe.serial('User Logout flow', () => {
  let user;

  const apiBaseURL = process.env.API_BASE_URL;

  test.beforeAll(async ({ request }) => {
    user = await createTestUser(request, apiBaseURL);
  });

  test.afterAll(async ({ request }) => {
    await deleteTestUser(request, apiBaseURL, user.email);
  });

  test('TC-AUTH-004 Logout @smoke', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();

    await loginPage.setEmail(user.email);
    await loginPage.setPassword(user.password);

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