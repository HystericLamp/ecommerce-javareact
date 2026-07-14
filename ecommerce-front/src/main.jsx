import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import './index.css'

import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";

import App from './app/App';
import { AuthProvider } from './context/AuthContext';
import { CartProvider } from "../src/features/cart/context/CartContext";

const stripeKey = import.meta.env.VITE_STRIPE_PUBLIC_KEY;

console.log("Stripe key:", stripeKey);
console.log("All env vars:", import.meta.env);

const stripePromise = stripeKey
  ? loadStripe(stripeKey)
  : Promise.resolve(null);

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <CartProvider>
          <Elements stripe={stripePromise}>
            <App />
          </Elements>
        </CartProvider>
      </AuthProvider>
    </BrowserRouter>
  </StrictMode>
);