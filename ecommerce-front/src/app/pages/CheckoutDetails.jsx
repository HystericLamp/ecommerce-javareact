import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCart } from "../../features/cart/context/CartContext";
import { useAuth } from "../../context/AuthContext";

import OrderItems from "../../features/checkout/components/OrderItems";
import CustomerForm from "../../features/checkout/components/CustomerForm";
import OrderSummary from "../../features/checkout/components/OrderSummary";

export default function Checkout() {
  const { cart, removeFromCart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();

  const [customer, setCustomer] = useState({
    email: user?.email || "",
    firstName: user?.firstName || "",
    lastName: user?.lastName || "",
    addressLine1: "",
    addressLine2: "",
    city: "",
    provinceState: "",
    postalCode: "",
    country: "Canada"
  });

  const [loading, setLoading] = useState(false);

  const total = cart.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  const handleChange = (e) => {
    const { name, value } = e.target;

    setCustomer((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleCheckout = async () => {
    setLoading(true);

    try {
      const payload = {
        userId: user ? user.id : null,
        ...customer,
        itemProducts: cart.map((item) => ({
          productId: item.id,
          quantity: item.quantity
        }))
      };

      const response = await fetch("/api/shop/checkout", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        throw new Error("Checkout failed");
      }

      const data = await response.json();

      navigate("/checkoutpayment", {
        state: {
          clientSecret: data.clientSecret,
          draftOrderId: data.draftOrderId
        }
      });
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  if (cart.length === 0) {
    return (
      <div className="max-w-4xl mx-auto py-20 text-center space-y-4">
        <h1 className="page-title">Checkout</h1>
        <p className="text-xl font-semibold">Your cart is empty</p>
        <p className="text-muted-foreground">
          Add items before proceeding to checkout.
        </p>
      </div>
    );
  }

  return (
    <div className="max-w-6xl mx-auto space-y-8">
      <h1 className="page-title">Checkout</h1>

      <div className="grid lg:grid-cols-3 gap-8">
        <div className="lg:col-span-2 space-y-8">
          <OrderItems
            items={cart}
            removeFromCart={removeFromCart}
          />

          <CustomerForm
            customer={customer}
            onChange={handleChange}
          />
        </div>

        <OrderSummary
          cart={cart}
          total={total}
          loading={loading}
          onCheckout={handleCheckout}
          onBack={() => navigate("/cart")}
        />
      </div>
    </div>
  );
}