import { useCart } from "../../features/cart/context/CartContext";

export default function Checkout() {
  const { cart, removeFromCart } = useCart();

  // Calculate total
  const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);

  return (
    <div>
      <h1>Checkout</h1>

      {cart.length === 0 ? (
        <p>Your cart is empty</p>
      ) : (
        <>
          <ul>
            {cart.map((item) => (
              <li key={item.id}>
                <h3>{item.quantity} {item.name}</h3>
                <p>${(item.price * item.quantity).toFixed(2)}</p>
                <button onClick={() => removeFromCart(item.id)}>
                  Remove
                </button>
              </li>
            ))}
          </ul>

          <h2>Total: ${total.toFixed(2)}</h2>
        </>
      )}
    </div>
  );
}