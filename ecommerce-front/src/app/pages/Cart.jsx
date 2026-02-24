import { useCart } from "../../features/cart/context/CartContext";

export default function Cart() {
  const { cart } = useCart();

  return (
    <div>
      <h1>Cart</h1>

      {cart.length === 0 ? (
        <p>Your cart is empty</p>
      ) : (
        cart.map(item => (
          <div key={item.id}>
            <h3>{item.name}</h3>
            <p>${item.price}</p>
          </div>
        ))
      )}
    </div>
  );
}