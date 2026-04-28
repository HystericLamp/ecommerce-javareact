import { useNavigate } from "react-router-dom";

import { useCart } from "../../features/cart/context/CartContext";

import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Separator } from "@/components/ui/separator";
import { Trash2 } from "lucide-react";

export default function Cart() {
  const { cart, removeFromCart, updateQuantity } = useCart();
  const navigate = useNavigate();

  const total = cart.reduce(
    (acc, item) => acc + item.price * item.quantity,
    0
  );

  return (
    <div className="max-w-4xl mx-auto space-y-8">
      {cart.length === 0 ? (
        <div className="text-center py-20 space-y-6">
          <div className="text-5xl">🛒</div>

          <div className="space-y-2">
            <p className="text-xl font-semibold text-foreground">
              Your cart is empty
            </p>
            <p className="text-sm text-muted-foreground">
              Start adding items to see them here.
            </p>
          </div>

          <Button
            variant="outline"
            className="max-w-xs mx-auto"
            onClick={() => navigate("/shop")}
          >
            Browse Products
          </Button>
        </div>
      ) : (
        <>
          <h1 className="page-title">Your Cart</h1>
          <div className="space-y-6">
            
            {/* Cart Items */}
            {cart.map((item) => (
              <Card key={item.id} className="flex items-center p-4 rounded-2xl shadow-sm">
                <img
                  src={item.image}
                  alt={item.name}
                  className="w-20 h-20 object-cover rounded-xl mr-4"
                />

                <CardContent className="flex-1 p-0">
                  <h3 className="font-medium text-lg text-foreground">{item.name}</h3>
                  <p className="text-muted-foreground">
                    ${item.price.toFixed(2)}
                  </p>

                  {/* Quantity Controls */}
                  <div className="flex items-center gap-2 mt-3">
                    <Button
                      variant="outline"
                      size="sm"
                      onClick={() => {
                        if (item.quantity === 1) {
                          removeFromCart(item.id);
                        } else {
                          updateQuantity(item.id, item.quantity - 1);
                        }
                      }}
                    >
                      -
                    </Button>

                    <span className="px-3 font-medium">{item.quantity}</span>

                    <Button
                      variant="outline"
                      size="sm"
                      onClick={() => updateQuantity(item.id, item.quantity + 1)}
                    >
                      +
                    </Button>
                  </div>
                </CardContent>

                {/* Price + Remove */}
                <div className="text-right flex flex-col items-end gap-3">
                  <p className="font-semibold text-foreground">
                    ${(item.price * item.quantity).toFixed(2)}
                  </p>

                  <Button
                    variant="ghost"
                    size="icon"
                    className="text-muted-foreground hover:text-destructive"
                    onClick={() => removeFromCart(item.id)}
                  >
                    <Trash2 className="w-4 h-4" />
                  </Button>
                </div>
              </Card>
            ))}

            <Separator />

            {/* Summary */}
            <div className="flex justify-between text-sm text-muted-foreground">
              <span>Items</span>
              <span>
                {cart.reduce((total, item) => total + item.quantity, 0)}
              </span>
            </div>
            <div className="flex justify-between items-center">
              <h2 className="text-xl font-semibold text-foreground">Total</h2>
              <p className="text-xl font-bold text-primary">
                ${total.toFixed(2)}
              </p>
            </div>

            <Button
              className="w-full text-lg py-6"
              disabled={cart.length === 0}
              onClick={() => navigate("/checkout")}
            >
              Proceed to Checkout
            </Button>

            <Button
              variant="outline"
              className="w-full"
              onClick={() => navigate("/shop")}
            >
              Continue Shopping
            </Button>
          </div>
        </>
      )}
    </div>
    
  );
}