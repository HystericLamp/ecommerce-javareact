import { useNavigate } from "react-router-dom";
import { useCart } from "../../features/cart/context/CartContext";
import { useAuth } from "../../context/AuthContext";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";

export default function Checkout() {
  const { cart, removeFromCart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();

  // Calculate total
  const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);

  const handleCheckout = async () => {
    if (!user) {
      navigate("/login");
      return;
    }

    const payload = {
      userId: user.id,
      itemProducts: cart.map(item => ({
        id: item.id,
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

    const data = await response.json();

    console.log(data);
  };

  return (
    <div className="max-w-5xl mx-auto space-y-8">
      <h1 className="page-title">Checkout</h1>

      {cart.length === 0 ? (
        <div className="text-center py-20 space-y-4">
          <p className="text-xl font-semibold text-foreground">
            Your cart is empty
          </p>
          <p className="text-sm text-muted-foreground">
            Add items before proceeding to checkout.
          </p>
        </div>
      ) : (
        <div className="grid md:grid-cols-3 gap-8">
          
          {/* LEFT: Items */}
          <div className="md:col-span-2 space-y-4">
            <h2 className="section-title">Order Items</h2>

            {cart.map((item) => (
              <Card key={item.id} className="p-4 flex justify-between items-center rounded-xl">
                <div>
                  <h3 className="font-medium text-foreground">
                    {item.quantity} × {item.name}
                  </h3>
                  <p className="text-sm text-muted-foreground">
                    ${item.price.toFixed(2)} each
                  </p>
                </div>

                <div className="flex items-center gap-4">
                  <p className="font-semibold text-foreground">
                    ${(item.price * item.quantity).toFixed(2)}
                  </p>

                  <Button
                    variant="ghost"
                    size="icon"
                    className="text-muted-foreground hover:text-destructive"
                    onClick={() => removeFromCart(item.id)}
                  >
                    ✕
                  </Button>
                </div>
              </Card>
            ))}
          </div>

          {/* RIGHT: Summary */}
          <div className="space-y-4">
            <Card className="p-6 rounded-xl space-y-4">
              <h2 className="text-lg font-semibold text-foreground">
                Order Summary
              </h2>

              <div className="flex justify-between text-sm text-muted-foreground">
                <span>Items</span>
                <span>
                  {cart.reduce((total, item) => total + item.quantity, 0)}
                </span>
              </div>

              <div className="flex justify-between items-center">
                <span className="font-medium text-foreground">Total</span>
                <span className="text-xl font-bold text-primary">
                  ${total.toFixed(2)}
                </span>
              </div>

              <Separator />

              <Button
                className="w-full text-lg py-6"
                onClick={handleCheckout}
              >
                Place Order
              </Button>
            </Card>

            <Button
              variant="outline"
              className="w-full"
              onClick={() => navigate("/cart")}
            >
              Back to Cart
            </Button>
          </div>
        </div>
      )}
    </div>
  );
}