import { useState } from "react";
import { useNavigate } from "react-router-dom";
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
  const navigate = useNavigate();

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [copied, setCopied] = useState(false);

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
      setError("Payment failed. Please use the test card provided.");
      setLoading(false);
      return;
    }

    if (
      result.paymentIntent &&
      result.paymentIntent.status === "succeeded"
    ) {
      navigate("/checkoutsuccess", {
        state: { draftOrderId }
      });
    }

    setLoading(false);
  };

  const handleCopy = async () => {
    await navigator.clipboard.writeText("4242424242424242");
    setCopied(true);
    setTimeout(() => setCopied(false), 1500);
  };

  return (
    <>
      <div className="text-sm bg-muted p-4 rounded-md space-y-1">
        <p className="font-medium">Test Card</p>
        <p>4242 4242 4242 4242</p>
        <p>Any future expiry, any CVC</p>

        <Button variant="outline" onClick={handleCopy}>
          {copied ? "Copied!" : "Copy Test Card"}
        </Button>
      </div>
      <Card className="p-6 rounded-xl space-y-6">
        <h2 className="text-lg font-semibold text-foreground">
          Payment Details
        </h2>
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
      </Card>
    </>
  );
}