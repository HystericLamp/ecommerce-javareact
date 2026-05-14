import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
  await page.addInitScript(() => {
    localStorage.clear();
    sessionStorage.clear();
  });
});

test('AC-CART-01 user can add item to cart', async ({ page }) => {
  await page.goto('/shop');

  const productCard = page.locator('.p-4').filter({
    has: page.getByRole('heading', {
      name: 'Colombian Supremo',
    }),
  });

  await productCard
    .getByRole('button', { name: 'Add' })
    .click();

  await expect(
    productCard.getByText(/1 in cart/i)
  ).toBeVisible();

  await page.locator('a[href="/cart"]').click();

  await expect(
    page.getByText(/colombian supremo/i)
  ).toBeVisible();
});

test('AC-CART-02 adding same item increments quanitty', async ({ page }) => {
  await page.goto('/shop');

  const productCard = page.locator('.p-4').filter({
    has: page.getByRole('heading', {
      name: 'Colombian Supremo',
    }),
  });

  // Check initial "Add" state
  await expect(
    productCard.getByRole('button', { name: 'Add' })
  ).toBeVisible();

  await productCard
    .getByRole('button', { name: 'Add' })
    .click();

  // Check after "add" state
  await expect(
    productCard.getByText(/1 in cart/i)
  ).toBeVisible();

  await expect(
    productCard.getByRole('button', { name: '+' })
  ).toBeVisible();

  await expect(
    productCard.getByRole('button', { name: '-' })
  ).toBeVisible();

  // Pressing "+" button
  await productCard.getByRole('button', {
    name: '+',
  }).click();

  await expect(
    productCard.getByText(/2 in cart/i)
  ).toBeVisible();
});

test('AC-CART-03 Do not add an item with 0 or negative quantity', async ({ page }) => {
  await page.goto('/shop');

  const productCard = page.locator('.p-4').filter({
    has: page.getByRole('heading', {
      name: 'Colombian Supremo',
    }),
  });

  // Press "Add"
  await productCard
    .getByRole('button', { name: 'Add' })
    .click();

  await expect(
    productCard.getByRole('button', { name: '-' })
  ).toBeVisible();

  // Press "-" button
  await productCard.getByRole('button', {
    name: '-',
  }).click();

  // Check that it can't go to 0 and the "Add" button is back
  await expect(
    productCard.getByRole('button', { name: 'Add' })
  ).toBeVisible();
});