import { useEffect, useState } from 'react';
import { CardElement, useStripe, useElements } from '@stripe/react-stripe-js';

// Basic Template for now
function CheckoutForm() {
  const stripe = useStripe();
  const elements = useElements();
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);

    try {
      // 1️⃣ Call your backend to create a PaymentIntent
      const response = await fetch("/api/payments/create-intent/1", {
        method: "POST",
      });
      const { clientSecret } = await response.json();

      // 2️⃣ Confirm the card payment using Stripe.js
      const result = await stripe.confirmCardPayment(clientSecret, {
        payment_method: {
          card: elements.getElement(CardElement),
        },
      });

      // 3️⃣ Handle result
      if (result.error) {
        alert(result.error.message);
      } else if (result.paymentIntent.status === "succeeded") {
        alert("Payment succeeded!");
      }

    } catch (err) {
      console.error(err);
      alert("Payment failed. See console for details.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <CardElement />
      <button type="submit" disabled={!stripe || loading}>
        {loading ? "Processing…" : "Pay"}
      </button>
    </form>
  );
}

export default CheckoutForm;