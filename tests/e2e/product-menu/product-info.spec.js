import { test, expect } from '@playwright/test';

test('AC-PRODUCT-MENU-01 - Get All Product Information @smoke', async ({ page }) => {
    await page.goto('/shop');

    // Main page heading
    await expect(
      page.getByRole('heading', {
        name: 'Coffee & Brew Gear'
      })
    ).toBeVisible();

    // Category headings
    await expect(
      page.getByRole('heading', {
        name: 'Coffee',
        exact: true
      })
    ).toBeVisible();

    await expect(
      page.getByRole('heading', {
        name: 'Brew Gear',
        exact: true
      })
    ).toBeVisible();

    await expect(
      page.getByRole('heading', {
        name: 'Accessories',
        exact: true
      })
    ).toBeVisible();

    await expect(
      page.getByRole('heading', {
        name: 'Snacks',
        exact: true
      })
    ).toBeVisible();

    // Product assertions
    await expect(
      page.getByText('Ethiopian Sunrise')
    ).toBeVisible();

    await expect(
      page.getByText('Classic French Press')
    ).toBeVisible();

    await expect(
      page.getByText('Stoneware Latte Mug')
    ).toBeVisible();

    await expect(
      page.getByText('Chocolate-Dipped Biscotti')
    ).toBeVisible();
});