import React from "react"; 
import { useState, useEffect } from "react";

export default function Shop() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/products/getAllProducts")
      .then((res) => res.json())
      .then((data) => {
        setProducts(data);
      })
      .catch((err) => console.error("Error fetching products:", err));
  }, []);

  return (
    <div>
      <h1>Shop Products</h1>
      
      <div style={{ display: "flex", flexWrap: "wrap", gap: "20px" }}>
        {products.map((product) => (
          <div
            key={product.id}
            style={{
              border: "1px solid #ccc",
              padding: "15px",
              width: "200px",
            }}
          >
            <h3>{product.name}</h3>
            <p>{product.description}</p>
            <strong>${product.price}</strong>
          </div>
        ))}
      </div>
    </div>
  );
}