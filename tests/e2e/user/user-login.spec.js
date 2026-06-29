import { test, expect } from '@playwright/test';

test.describe.serial('User flow', () => {
  const email = `doe-${Date.now()}-${Math.random()}@email.com`;
  const password = 'Lachesis123';

  const apiBaseURL = process.env.API_BASE_URL;

  test.afterAll(async ({ request }) => {
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
    await page.goto('/login');
    await page.getByText('Sign up').click();

    // Input new user details
    await page.getByLabel('Name').fill('John Doe');
    await page.getByLabel('Email').fill(email);

    await page
      .getByLabel('Password', { exact: true })
      .fill(password);

    await page
      .getByLabel('Confirm Password')
      .fill(password);

    // Submit
    const dialogPromise = page.waitForEvent('dialog');

    await page.getByRole('button', { name: 'Create Account' }).click();

    const dialog = await dialogPromise;

    expect(dialog.message()).toBe('New Member created!');
    await dialog.accept();
  });

  test('AC-USER-02 login as an existing user @smoke', async ({ page }) => {
    await page.goto('/login');
    
    await page.getByLabel('Email').fill(email);
    await page.getByLabel('Password').fill(password);

    const dialogPromise = page.waitForEvent('dialog');

    await page
      .locator('form')
      .getByRole('button', { name: 'Login' })
      .click();

    const dialog = await dialogPromise;

    expect(dialog.message()).toBe('Logged in!');
    await dialog.accept();

    await expect(page.getByText(email)).toBeVisible();
  });

  test('logout test', async ({ page }) => {
    await page.goto('/login');

    await page.getByLabel('Email').fill(email);
    await page.getByLabel('Password').fill(password);

    const dialogPromise = page.waitForEvent('dialog');

    await page
      .locator('form')
      .getByRole('button', { name: 'Login' })
      .click();

    const dialog = await dialogPromise;
    await dialog.accept();

    await page.getByRole('button', { name: 'Logout' }).click();

    await expect(
      page.getByRole('button', { name: 'Login' })
    ).toBeVisible();
  });
});