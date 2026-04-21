import { Coffee } from "lucide-react";

export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            colors: {
                coffee: {
                    bg: "#f7f3ef",
                    primary: "#6b4f3b",
                    primaryHover: "#5a3f2f",
                    heading: "#3e2c23",
                    border: "#e0d6cf",
                    text: "#2f1f17",
                    price: "#4b2e2b",
                }
            }
        },
    },
    plugins: [],
}