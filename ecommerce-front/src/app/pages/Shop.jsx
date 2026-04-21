import React from "react"; 
import { useState, useEffect } from "react";
import { API, API_URL } from "../../config/api";
import { ProductCard } from "@/components/ProductCard";

import { useCart } from "@/features/cart/context/CartContext";

export default function Shop() {
  const [products, setProducts] = useState([]);
  const { cart, addToCart, updateQuantity } = useCart();

  const getCartItem = (productId) => {
    return cart.find((item) => item.id === productId);
  };

  useEffect(() => {
    fetch(`${API.PRODUCTS}/getAllProducts`)
      .then(async (res) => {
        if (!res.ok) {
          const text = await res.text();
          throw new Error(`HTTP ${res.status}: ${text}`);
        }
        return res.json();
      })
      .then((data) => {
        setProducts(data);
      })
      .catch((err) => console.error("Error fetching products:", err));
  }, []);

  const groupedProducts = products.reduce((acc, product) => {
    const categoryName = product.category?.name || "Uncategorized";

    if (!acc[categoryName]) {
      acc[categoryName] = [];
    }

    acc[categoryName].push(product);
    return acc;
  }, {});

  return (
    <>
      <h1 className="page-title">Coffee & Brew Gear</h1>

      {Object.entries(groupedProducts).map(([category, items]) => (
        <div key={category} className="mb-12">
          <h2 className="section-title">{category}</h2>

          <div className="grid gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
            {items.map((product) => (
              <ProductCard
                key={product.id}
                product={product}
                cartItem={getCartItem(product.id)}
                addToCart={addToCart}
                updateQuantity={updateQuantity}
              />
            ))}
          </div>
        </div>
      ))}
    </>
  );
}