import { useState } from "react";

import { useCart } from "@/features/cart/context/CartContext";

import { Button } from "@/components/ui/button";

import {
    CardElement,
    useElements,
    useStripe,
} from "@stripe/react-stripe-js";

import { confirmPayment } from "./stripeService";

export default function StripePaymentForm ({
    clientSecret,
    onSuccess
}) {
    const stripe = useStripe();
    const elements = useElements();
    const { cart } = useCart();

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const total = cart.reduce(
        (sum, item) => sum + item.price * item.quantity, 0
    );

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);
        setError("");

        try {
            console.log("clientSecret:", clientSecret);
            
            const result = await confirmPayment({
                stripe,
                elements,
                clientSecret,
            });

            if(result.error) {
                setError(result.error.message);
                return;
            }

            if (result.paymentIntent?.status === "succeeded") {
                onSuccess(result.paymentIntent);
            }
        }
        catch (err) {
            setError(err.message);
        }
        finally {
            setLoading(false);
        }
    };

    return(
        <form 
            onSubmit={handleSubmit}
            className="space-y-6"
        >
            <div
                className="
                    rounded-xl
                    border
                    bg-card
                    p-6
                    shadow-sm
                    space-y-4
                "
            >
                <label className="text-sm font-semibold uppercase tracking-wide text-muted-foreground">
                    Card Information
                </label>

                <div
                    data-testid="stripe-payment-form-card-div"
                    className="
                        rounded-xl
                        border
                        bg-background
                        px-4
                        py-5
                        shadow-sm
                        transition-all
                        focus-within:ring-2
                        focus-within:ring-primary"
                >
                    <CardElement
                        options={{
                            style: {
                            base: {
                                fontSize: "18px",
                                color: "#111827",
                                fontFamily:
                                "Inter, system-ui, sans-serif",
                                "::placeholder": {
                                color: "#9ca3af",
                                },
                            },
                            },
                        }}
                    />
                </div>
            </div>

            <div className="border-t pt-6">
                <div className="flex justify-between">
                    <span>Total</span>
                    <span className="font-bold text-lg">
                        ${total.toFixed(2)}
                    </span>
                </div>
            </div>

            {error && (
                <div
                    data-testid="payment-error" 
                    className="rounded-md border border-red-200 bg-red-50 p-3"
                >
                    <p className="text-red-500 text-sm">
                        {error}
                    </p>
                </div>
            )}

            <Button
                data-testid="payment-submit"
                type="submit"
                className="
                    w-full
                    h-14
                    text-lg
                    font-semibold
                    shadow-lg
                    transition-all
                    hover:scale-[1.01]
                "
                disabled={!stripe || loading}
            >
                {loading
                    ? "Processing Payment..."
                    : "Complete Purchase"
                }
            </Button>
        </form>
    );
}