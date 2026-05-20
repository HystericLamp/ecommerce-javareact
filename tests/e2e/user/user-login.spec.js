import { test, expect } from '@playwright/test';
test.describe.serial('User flow', () => {
  const email = `doe-${Date.now()}@email.com`;
  const password = 'Lachesis123';

  test('AC-USER-01 register new member', async ({ page }) => {
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

  test('AC-USER-02 login as an existing user', async ({ page }) => {
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
});