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

if (!stripeKey) {
  throw new Error("VITE_STRIPE_PUBLIC_KEY is missing");
}

const stripePromise = loadStripe(stripeKey);

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