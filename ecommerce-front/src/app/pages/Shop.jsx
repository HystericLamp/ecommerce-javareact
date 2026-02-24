import { useCart } from "../../features/cart/context/CartContext";

const products = [
  { id: 1, name: "Espresso", price: 4.99 },
  { id: 2, name: "Machiato", price: 4.50 },
  { id: 3, name: "Cappuchino", price: 6.99 },
  { id: 4, name: "Latte", price: 6.50 },
  { id: 5, name: "Frappuchino", price: 5.50 },
  { id: 6, name: "Tea", price: 3.99 }
];

export default function Shop() {
  const { addToCart } = useCart();

  return (
    <div>
      <h1>Shop</h1>
      {products.map(product => (
        <div key={product.id}>
          <h3>{product.name}</h3>
          <p>${product.price}</p>
          <button onClick={() => addToCart(product)}>
            Add to Cart
          </button>
        </div>
      ))}
    </div>
  );
}