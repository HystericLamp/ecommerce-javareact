// tests/home-page.spec.js
import { test, expect } from '@playwright/test';

test('"/home" page should exist', async ({ page, baseURL }) => {
  // Replace with your base URL or hard-code the full URL
  const response = await page.goto('/home', { waitUntil: 'domcontentloaded' });

  // Check response
  expect(response).not.toBeNull();
  expect(response.ok()).toBeTruthy();

  // check we ended up on the right URL string
  await expect(page).toHaveURL(/\/home/);
});
