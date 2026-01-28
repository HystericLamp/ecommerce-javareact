// tests/home-page.spec.js
import { test, expect } from '@playwright/test';

test('"/home" page should exist', async ({ page, baseURL }) => {
  const response = await page.goto('/home', { waitUntil: 'domcontentloaded' });

  // Check response
  expect(response).not.toBeNull();
  expect(response.ok()).toBeTruthy();

  // check we ended up on the right URL string
  await expect(page).toHaveURL(/\/home/);
});

test('"/cart" page should exist', async ({ page, baseURL }) => {
  const response = await page.goto('/cart', { waitUntil: 'domcontentloaded' });

  // Check response
  expect(response).not.toBeNull();
  expect(response.ok()).toBeTruthy();

  // check we ended up on the right URL string
  await expect(page).toHaveURL(/\/cart/);
});

test('"/orderconfirmation" page should exist', async ({ page, baseURL }) => {
  const response = await page.goto('/orderconfirmation', { waitUntil: 'domcontentloaded' });

  // Check response
  expect(response).not.toBeNull();
  expect(response.ok()).toBeTruthy();

  // check we ended up on the right URL string
  await expect(page).toHaveURL(/\/orderconfirmation/);
});

test('"/productdetail" page should exist', async ({ page, baseURL }) => {
  const response = await page.goto('/productdetail', { waitUntil: 'domcontentloaded' });

  // Check response
  expect(response).not.toBeNull();
  expect(response.ok()).toBeTruthy();

  // check we ended up on the right URL string
  await expect(page).toHaveURL(/\/productdetail/);
});

test('"/shop" page should exist', async ({ page, baseURL }) => {
  const response = await page.goto('/shop', { waitUntil: 'domcontentloaded' });

  // Check response
  expect(response).not.toBeNull();
  expect(response.ok()).toBeTruthy();

  // check we ended up on the right URL string
  await expect(page).toHaveURL(/\/shop/);
});

test('"/laugh" page should not exist', async ({ page, baseURL }) => {
  const response = await page.goto('/laugh', { waitUntil: 'domcontentloaded' });

  // response should exist
  expect(response).not.toBeNull();

  // check if a "404 - Page Not Found" page
  await expect(page.getByText(/404 - page not found/i)).toBeVisible();
});