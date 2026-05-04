import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

import { useCart } from "../../features/cart/context/CartContext";

export default function CheckoutSuccess() {
  const navigate = useNavigate();
  const { clearCart } = useCart();

  useEffect(() => {
    localStorage.removeItem("cart");
    clearCart();
  }, []);

  return (
    <div className="max-w-3xl mx-auto py-20 px-4">
      <Card className="p-8 rounded-2xl shadow-sm text-center space-y-6">
        
        {/* Success Icon */}
        <div className="text-5xl">
          ✅
        </div>

        {/* Title */}
        <h1 className="text-3xl font-bold text-foreground">
          Payment Successful
        </h1>

        {/* Message */}
        <p className="text-muted-foreground text-sm">
          Thank you for your purchase. Your order has been received and is now being processed.
        </p>

        {/* Info */}
        <div className="text-sm text-muted-foreground space-y-1">
          <p>If you have any questions, feel free to contact support.</p>
        </div>

        {/* Actions */}
        <div className="flex flex-col sm:flex-row gap-4 justify-center pt-4">
          <Button
            onClick={() => navigate("/")}
            className="w-full sm:w-auto"
          >
            Continue Shopping
          </Button>

          <Button
            variant="outline"
            onClick={() => navigate("/orders")}
            className="w-full sm:w-auto"
          >
            View Orders
          </Button>
        </div>
      </Card>
    </div>
  );
}