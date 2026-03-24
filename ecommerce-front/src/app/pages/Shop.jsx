import React from "react"; 
import { useState, useEffect } from "react";
import { API, API_URL } from "../../config/api";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

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
    <div className="min-h-screen bg-[#f7f3ef] px-6 py-10">
      <div className="max-w-6xl mx-auto">
        <h1 className="text-4xl font-semibold text-[#3e2c23] mb-10">
          Coffee & Brew Gear
        </h1>

        {Object.entries(groupedProducts).map(([category, items]) => (
          <div key={category} className="mb-12">
            <h2 className="text-2xl font-medium text-[#6b4f3b] mb-6 border-b border-[#e0d6cf] pb-2">
              {category}
            </h2>

            <div className="grid gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
              {items.map((product) => {
                const cartItem = getCartItem(product.id);

                return (
                  <Card
                    key={product.id}
                    className="rounded-2xl shadow-sm hover:shadow-md transition-all duration-200 hover:-translate-y-1 bg-white"
                  >
                    <CardContent className="p-4 flex flex-col h-full">
                      
                      <h3 className="text-lg font-semibold text-[#2f1f17]">
                        {product.name}
                      </h3>

                      <p className="text-sm text-gray-600 mt-1 line-clamp-3">
                        {product.description}
                      </p>

                      <div className="flex-grow" />

                      <div className="mt-4 flex items-center justify-between">
                        <span className="text-base font-bold text-[#4b2e2b]">
                          ${(product.price / 100).toFixed(2)}
                        </span>

                        {!cartItem ? (
                          <Button
                            size="sm"
                            className="bg-[#6b4f3b] hover:bg-[#5a3f2f]"
                            onClick={() =>
                              addToCart({
                                ...product,
                                price: product.price / 100,
                                quantity: 1,
                              })
                            }
                          >
                            Add
                          </Button>
                        ) : (
                          <div className="flex flex-col items-end gap-1">
                            <span className="text-xs">
                              <b>{cartItem.quantity}</b> in cart
                            </span>

                            <div className="flex items-center gap-2">
                              <Button
                                size="sm"
                                variant="outline"
                                onClick={() =>
                                  updateQuantity(product.id, cartItem.quantity - 1)
                                }
                              >
                                -
                              </Button>
                              <Button
                                size="sm"
                                variant="outline"
                                onClick={() =>
                                  updateQuantity(product.id, cartItem.quantity + 1)
                                }
                              >
                                +
                              </Button>
                            </div>
                          </div>
                        )}
                      </div>
                    </CardContent>
                  </Card>
                );
              })}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}