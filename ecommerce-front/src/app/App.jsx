import { Routes, Route } from "react-router-dom";
import { CartProvider } from "../features/cart/context/CartContext";

const pages = import.meta.glob("./pages/*.jsx", { eager: true });

function App() {
  const Home = pages["./pages/Home.jsx"]?.default;

  return (
    <CartProvider>
      <Routes>
        {/* Default route for "/" */}
        {Home && <Route index element={<Home />} />}

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
    </CartProvider>
  );
}

export default App;
