import { Routes, Route } from "react-router-dom";

const pages = import.meta.glob("./pages/*.jsx", { eager: true });

function App() {
  return (
    <Routes>
      {Object.entries(pages).map(([path, module]) => {
        const name = path
          .replace("./pages/", "")
          .replace(".jsx", "")
          .toLowerCase();
        const Component = module.default;
        return <Route key={name} path={`/${name}`} element={<Component />} />;
      })}
    </Routes>
  );
}

export default App;
