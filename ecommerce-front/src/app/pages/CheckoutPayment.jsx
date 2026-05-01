import { useState } from "react";
import {
  CardElement,
  useStripe,
  useElements
} from "@stripe/react-stripe-js";

import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

export default function StripePaymentForm({
  clientSecret
}) {
  const stripe = useStripe();
  const elements = useElements();

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  const handlePayment = async (e) => {
    e.preventDefault();

    if (!stripe || !elements) return;

    setLoading(true);
    setError("");

    const card = elements.getElement(CardElement);

    const result = await stripe.confirmCardPayment(
      clientSecret,
      {
        payment_method: {
          card
        }
      }
    );

    if (result.error) {
      setError(result.error.message);
      setLoading(false);
      return;
    }

    if (
      result.paymentIntent &&
      result.paymentIntent.status === "succeeded"
    ) {
      setSuccess(true);
    }

    setLoading(false);
  };

  return (
    <Card className="p-6 rounded-xl space-y-6">
      <h2 className="text-lg font-semibold text-foreground">
        Payment Details
      </h2>

      {success ? (
        <div className="space-y-2">
          <p className="text-green-600 font-medium">
            Payment successful.
          </p>

          <p className="text-sm text-muted-foreground">
            Your order is being finalized.
          </p>
        </div>
      ) : (
        <form
          onSubmit={handlePayment}
          className="space-y-6"
        >
          <div className="border rounded-md p-4 bg-background">
            <CardElement
              options={{
                style: {
                  base: {
                    fontSize: "16px"
                  }
                }
              }}
            />
          </div>

          {error && (
            <p className="text-sm text-red-500">
              {error}
            </p>
          )}

          <Button
            type="submit"
            className="w-full text-lg py-6"
            disabled={!stripe || loading}
          >
            {loading
              ? "Processing Payment..."
              : "Pay Now"}
          </Button>
        </form>
      )}
    </Card>
  );
}