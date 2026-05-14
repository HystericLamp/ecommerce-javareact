// import { test, expect } from '@playwright/test';

// test('"/home" page should exist', async ({ page, baseURL }) => {
//   const response = await page.goto('/home', { waitUntil: 'domcontentloaded' });

//   expect(response).not.toBeNull();
//   expect(response.ok()).toBeTruthy();

//   await expect(page).toHaveURL(/\/home/);
// });

// test('"/cart" page should exist', async ({ page, baseURL }) => {
//   const response = await page.goto('/cart', { waitUntil: 'domcontentloaded' });

//   expect(response).not.toBeNull();
//   expect(response.ok()).toBeTruthy();

//   await expect(page).toHaveURL(/\/cart/);
// });

// test('"/shop" page should exist', async ({ page, baseURL }) => {
//   const response = await page.goto('/shop', { waitUntil: 'domcontentloaded' });

//   expect(response).not.toBeNull();
//   expect(response.ok()).toBeTruthy();

//   await expect(page).toHaveURL(/\/shop/);
// });

// test('"/laugh" page should not exist', async ({ page, baseURL }) => {
//   const response = await page.goto('/laugh', { waitUntil: 'domcontentloaded' });

//   // response should exist
//   expect(response).not.toBeNull();

//   // check if a "404 - Page Not Found" page
//   await expect(page.getByText(/404 - page not found/i)).toBeVisible();
// });