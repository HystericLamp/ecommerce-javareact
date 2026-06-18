import { CardElement } from "@stripe/react-stripe-js";

export async function confirmPayment({
    stripe,
    elements,
    clientSecret
}) {
    if(!stripe || !elements) {
        throw new Error("Stripe not initialized");
    }

    const card = elements.getElement(CardElement);

    if(!card) {
        throw new Error("Card element not found");
    }

    const result = await stripe.confirmCardPayment(
        clientSecret,
        {
            payment_method: {
                card,
            },
        }
    );

    return result;
}