import { Routes, Route } from "react-router-dom";

const pages = import.meta.glob("./pages/*.jsx", { eager: true });

function App() {
  return (
    <Routes>
      {/* Default route for "/" */}
      {pages["./pages/Home.jsx"] && (
        <Route index element={pages["./pages/Home.jsx"].default()} />
      )}

      {/* Dynamically route all pages */}
      {Object.entries(pages).map(([path, module]) => {
        const name = path
          .replace("./pages/", "")
          .replace(".jsx", "")
          .toLowerCase();
        const Component = module.default;

        return <Route key={name} path={`/${name}`} element={<Component />} />;
      })}

      {/* 404 catch-all route */}
      <Route path="*" element={<h1>404 - Page Not Found</h1>} />
    </Routes>
  );
}

export default App;
