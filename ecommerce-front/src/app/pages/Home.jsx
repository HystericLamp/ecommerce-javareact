import React from "react";
import { useNavigate } from "react-router-dom";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

const Home = () => {
    const navigate = useNavigate();

    return (
        <div className="flex items-center justify-center min-h-[70vh]">
            <Card className="max-w-xl w-full p-8 text-center space-y-6">
            
            <div className="space-y-2">
                <h1 className="text-3xl font-semibold text-foreground">
                Coffee Shop Demo ☕
                </h1>
                <p className="text-muted-foreground">
                A sample e-commerce experience built for demonstration purposes.
                </p>
            </div>

            <div className="bg-muted rounded-lg p-4 text-sm text-muted-foreground">
                <p>
                ⚠️ This is a demo application. No real purchases are made and no
                payments are processed. All products and transactions are simulated.
                </p>
            </div>

            <Button
                className="w-full"
                onClick={() => navigate("/shop")}
            >
                Browse Products
            </Button>
            </Card>
        </div>
        );
};

export default Home;