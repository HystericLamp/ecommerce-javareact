import { useNavigate } from "react-router-dom";
import { useState, useContext } from "react";
import { login } from "../../api/auth";
import { AuthContext } from "../../context/AuthContext";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";

function Login() {
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = await login(email, password);
      user(token);
      alert("Logged in!");
      navigate("/shop")
    } catch (err) {
      alert("Invalid credentials");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-[70vh]">
      <Card className="w-full max-w-md p-6 space-y-6">
        
        {/* Header */}
        <div className="space-y-2 text-center">
          <h1 className="text-2xl font-semibold text-foreground">
            Welcome back
          </h1>
          <p className="text-sm text-muted-foreground">
            Enter your credentials to continue
          </p>
        </div>

        {error && (
          <p className="text-sm text-destructive">{error}</p>
        )}

        {/* Form */}
        <form onSubmit={handleSubmit} className="space-y-4">
          
          {/* Email */}
          <div className="space-y-2">
            <Label htmlFor="email">Email</Label>
            <Input
              id="email"
              type="email"
              placeholder="you@example.com"
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          {/* Password */}
          <div className="space-y-2">
            <Label htmlFor="password">Password</Label>
            <Input
              id="password"
              type="password"
              placeholder="Password"
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <Button type="submit" className="w-full">
            Login
          </Button>
        </form>

        { /* Register New User link */ }
        <p className="text-sm text-center text-muted-foreground">
          Don’t have an account?{" "}
          <span
            className="text-primary cursor-pointer hover:underline"
            onClick={() => navigate("/register")}
          >
            Sign up
          </span>
        </p>
      </Card>
    </div>
  );
}

export default Login;