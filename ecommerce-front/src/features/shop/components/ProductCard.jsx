import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

export function ProductCard({
  product,
  cartItem,
  addToCart,
  updateQuantity,
}) {
  return (
    <div 
      data-testid="product-card"
      data-product-id={product.id}
    >
      <Card className="rounded-2xl shadow-sm hover:shadow-md transition-all hover:-translate-y-1 bg-white">
        <CardContent className="p-4 flex flex-col h-full">
          
          <h3
            data-testid="product-name"
            className="text-lg font-semibold text-coffee-text"
          >
            {product.name}
          </h3>

          <p 
            data-testid="product-description"
            className="text-sm text-gray-600 mt-1 line-clamp-3"
          >
            {product.description}
          </p>

          <div className="grow" />

          <div className="mt-4 flex items-center justify-between">
            <span
              data-testid="product-price" 
              className="text-base font-bold text-coffee-price"
            >
              ${(product.price / 100).toFixed(2)}
            </span>

            {!cartItem ? (
              <Button
                data-testid="add-button"
                size="sm"
                onClick={() =>
                  addToCart({
                    ...product,
                    price: product.price / 100,
                    quantity: 1,
                  })
                }
              >
                Add
              </Button>
            ) : (
              <div className="flex flex-col items-end gap-1">
                <span data-testid="quantity" className="text-xs">
                  <b>{cartItem.quantity}</b> in cart
                </span>

                <div className="flex items-center gap-2">
                  <Button
                    data-testid="decrease-button"
                    size="sm"
                    variant="outline"
                    onClick={() =>
                      updateQuantity(product.id, cartItem.quantity - 1)
                    }
                  >
                    -
                  </Button>

                  <Button
                    data-testid="increase-button"
                    size="sm"
                    variant="outline"
                    onClick={() =>
                      updateQuantity(product.id, cartItem.quantity + 1)
                    }
                  >
                    +
                  </Button>
                </div>
              </div>
            )}
          </div>

        </CardContent>
      </Card>
    </div>
  );
}