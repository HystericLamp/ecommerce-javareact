import { useCart } from "../../features/cart/context/CartContext";

import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Separator } from "@/components/ui/separator";
import { Trash2 } from "lucide-react";

export default function Cart() {
  const { cart, removeFromCart, updateQuantity } = useCart();

  const total = cart.reduce(
    (acc, item) => acc + item.price * item.quantity,
    0
  );

  return (
    <div className="max-w-4xl mx-auto p-6">
      <h1 className="text-3xl font-semibold mb-6">Your Cart</h1>

      {cart.length === 0 ? (
        <div className="text-center py-20 text-muted-foreground">
          Your cart is empty 🛒
        </div>
      ) : (
        <div className="space-y-6">
          {/* Cart Items */}
          {cart.map((item) => (
            <Card key={item.id} className="flex items-center p-4">
              <img
                src={item.image}
                alt={item.name}
                className="w-20 h-20 object-cover rounded-xl mr-4"
              />

              <CardContent className="flex-1 p-0">
                <h3 className="font-medium text-lg">{item.name}</h3>
                <p className="text-muted-foreground">
                  ${item.price.toFixed(2)}
                </p>

                {/* Quantity Controls */}
                <div className="flex items-center gap-2 mt-3">
                  <Button
                    variant="outline"
                    size="sm"
                    onClick={() =>
                      updateQuantity(item.id, item.quantity - 1)
                    }
                  >
                    -
                  </Button>

                  <span className="px-3">{item.quantity}</span>

                  <Button
                    variant="outline"
                    size="sm"
                    onClick={() =>
                      updateQuantity(item.id, item.quantity + 1)
                    }
                  >
                    +
                  </Button>
                </div>
              </CardContent>

              {/* Price + Remove */}
              <div className="text-right flex flex-col items-end gap-3">
                <p className="font-semibold">
                  ${(item.price * item.quantity).toFixed(2)}
                </p>

                <Button
                  variant="ghost"
                  size="icon"
                  onClick={() => removeFromCart(item.id)}
                >
                  <Trash2 className="w-4 h-4 text-red-500" />
                </Button>
              </div>
            </Card>
          ))}

          <Separator />

          {/* Summary */}
          <div className="flex justify-between items-center">
            <h2 className="text-xl font-semibold">Total</h2>
            <p className="text-xl font-bold">${total.toFixed(2)}</p>
          </div>

          <Button className="w-full text-lg py-6">
            Proceed to Checkout
          </Button>
        </div>
      )}
    </div>
  );
}