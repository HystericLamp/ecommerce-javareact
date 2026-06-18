import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

import StripePaymentForm from "@/features/checkout/paymentProviders/StripePaymentForm";

import { Card } from "@/components/ui/card";
import { CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

export default function CheckoutPayment({}) {
  const navigate = useNavigate();
  const location = useLocation();

  const {
    clientSecret,
    draftOrderId,
  } = location.state || {};

  if (!clientSecret) {
    return (
      <Card className="max-w-2xl mx-auto p-8">
        <h2 className="text-xl font-semibold mb-2">
          Payment Session Missing
        </h2>

        <p className="text-muted-foreground mb-4">
          Your payment session could not be found.
        </p>

        <Button
          onClick={() => navigate("/checkout")}
        >
          Return to Checkout
        </Button>
      </Card>
    );
  }

  const [copied, setCopied] = useState(false);

  const handleCopy = async () => {
    await navigator.clipboard.writeText("4242424242424242");
    setCopied(true);
    setTimeout(() => setCopied(false), 1500);
  };

  return (
    <div className="mx-auto max-w-2xl space-y-6">
      <Card className="mb-6 bg-muted/50">
        <CardContent className="p-4">
          <div className="flex items-start justify-between">
            <div>
              <p className="font-semibold">
                Stripe Test Card
              </p>

              <p className="font-mono mt-2">
                4242 4242 4242 4242
              </p>

              <p className="text-sm text-muted-foreground mt-1">
                Any future expiry • Any CVC
              </p>
            </div>

            <Button
              variant="outline"
              onClick={handleCopy}
            >
              {copied
                ? "Copied!"
                : "Copy"}
            </Button>
          </div>
        </CardContent>
      </Card>
      <Card
        className="
          mx-auto
          max-w-2xl
          p-8
          border
          shadow-xl
          bg-linear-to-b
          from-background
          to-muted/30
        "
      >
        <div className="flex items-center justify-between mb-8">
          <div>
            <h2 className="text-3xl font-bold">
              Payment Details
            </h2>

            <p className="text-muted-foreground mt-1">
              Checkout powered by Stripe
            </p>
          </div>
        </div>
      
        <StripePaymentForm
          clientSecret={clientSecret}
          onSuccess={() => 
            navigate("/checkoutsuccess", {
              state: {
                draftOrderId,
              },
            })
          }
        />
      </Card>
    </div>
  );
}