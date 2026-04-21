import { Outlet } from "react-router-dom";

export function MainLayout() {
  return (
    <div className="min-h-screen bg-coffee-bg px-6 py-10">
      <div className="max-w-6xl mx-auto">
        <Outlet />
      </div>
    </div>
  );
}