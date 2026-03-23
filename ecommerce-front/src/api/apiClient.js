export function getAuthHeaders() {
  const auth = JSON.parse(localStorage.getItem("auth"));

  return {
    "Content-Type": "application/json",
    Authorization: `Bearer ${auth?.token}`,
  };
}