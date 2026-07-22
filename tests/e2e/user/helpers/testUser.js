import { expect } from '@playwright/test';

export async function createTestUser(request, apiBaseURL) {
  const email = `user-${Date.now()}-${Math.random()}@email.com`;
  const password = "Password123!";

  const response = await request.post(`${apiBaseURL}/api/users/newUser`, {
    data: {
      name: "John Doe",
      email,
      password,
    },
  });

  expect(response.ok()).toBeTruthy();

  return { email, password };
}

export async function deleteTestUser(request, apiBaseURL, email) {
  await request.delete(`${apiBaseURL}/api/users/deleteUser/byEmail`, {
    params: { email },
  });
}