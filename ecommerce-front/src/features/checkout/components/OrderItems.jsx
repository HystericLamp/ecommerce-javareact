import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

export default function OrderItems ({
  items,
  removeFromCart
}) {
  return (
    <div className="space-y-4">
      <h2 className="section-title">Order Items</h2>

      {items.map((item) => (
        <Card
          key={item.id}
          className="p-4 flex justify-between items-center rounded-xl"
        >
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
              onClick={() => removeFromCart(item.id)}
            >
              ✕
            </Button>
          </div>
        </Card>
      ))}
    </div>
  );
}