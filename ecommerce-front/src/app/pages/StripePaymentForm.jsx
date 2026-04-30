import { CardElement, useStripe, useElements } from "@stripe/react-stripe-js";

export default function StripePaymentForm({ clientSecret }) {
  const stripe = useStripe();
  const elements = useElements();

  const handlePay = async () => {
    const card = elements.getElement(CardElement);

    const result = await stripe.confirmCardPayment(clientSecret, {
      payment_method: {
        card
      }
    });

    console.log(result);
  };

  return (
    <>
      <CardElement />
      <button onClick={handlePay}>Pay Now</button>
    </>
  );
}