import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";

export default function OrderSummary({
  cart,
  total,
  loading,
  onCheckout,
  onBack
}) {
  const itemCount = cart.reduce(
    (sum, item) => sum + item.quantity, 0
  );

  return (
    <div
      data-testid="order-summary-root"
      className="space-y-4"
    >
      <Card className="p-6 rounded-xl space-y-4 sticky top-6">
        <h2 className="text-lg font-semibold">
          Order Summary
        </h2>

        <div className="flex justify-between text-sm text-muted-foreground">
          <span>Items</span>
          <span data-testid="item-count">{itemCount}</span>
        </div>

        <div className="flex justify-between items-center">
          <span className="font-medium">Total</span>

          <span
            data-testid="order-total" 
            className="text-xl font-bold text-primary"
          >
            ${total.toFixed(2)}
          </span>
        </div>

        <Separator />

        <Button
          data-testid="checkout-submit"
          type="submit"
          className="w-full text-lg py-6"
          disabled={loading}
        >
          {loading
            ? "Processing..."
            : "Continue to Payment"}
        </Button>
      </Card>

      <Button
        data-testid="checkout-back-to-cart"
        type="button"
        variant="outline"
        className="w-full"
        onClick={onBack}
      >
        Back to Cart
      </Button>
    </div>
  );
}