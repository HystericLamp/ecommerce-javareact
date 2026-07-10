import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { QuantityInput } from "@/components/QuantityInput";

export default function OrderItems({
  items,
  removeFromCart,
  updateQuantity
}) {
  return (
    <div className="space-y-4">
      <h2 className="section-title">Order Items</h2>

      {items.map((item) => (
        <Card
          key={item.id}
          data-testid={`order-item-${item.id}`}
          className="p-4 flex justify-between items-center rounded-xl"
        >
          <div>
            <h3 className="font-medium text-foreground">
              {item.name}
            </h3>

            <p className="text-sm text-muted-foreground">
              ${item.price.toFixed(2)} each
            </p>
          </div>

          <div className="flex items-center gap-4">
            <div className="flex items-center gap-2">
              <Button
                aria-label={`Decrease quantity of ${item.name}`}
                data-testid={`decrease-${item.id}`}
                type="button"
                variant="outline"
                size="icon"
                onClick={() =>
                  updateQuantity(item.id, Math.max(1, item.quantity - 1))
                }
              >
                −
              </Button>

              <QuantityInput
                data-testid={`quantity-${item.id}`}
                quantity={item.quantity}
                onChange={(newQuantity) =>
                  updateQuantity(item.id, newQuantity)
                }
              />

              <Button
                aria-label={`Increase quantity of ${item.name}`}
                data-testid={`increase-${item.id}`}
                type="button"
                variant="outline"
                size="icon"
                onClick={() =>
                  updateQuantity(item.id, item.quantity + 1)
                }
              >
                +
              </Button>
            </div>

            <p 
              data-testid={`order-item-cost-${item.id}`}
              className="font-semibold text-foreground"
            >
              ${(item.price * item.quantity).toFixed(2)}
            </p>

            <Button
              data-testid={`remove-${item.id}`}
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