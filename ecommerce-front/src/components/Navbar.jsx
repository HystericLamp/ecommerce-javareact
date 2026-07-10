import { Link, NavLink } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { ShoppingCart } from "lucide-react";
import { useContext } from "react";

import { AuthContext } from "../context/AuthContext";

function Navbar() {
  const { auth, logoutUser } = useContext(AuthContext);

  return (
    <header className="border-b bg-background sticky top-0 z-50">
        <div
            data-testid="navbar-root"
            className="container mx-auto flex h-16 items-center justify-between px-4"
        >
            
            {/* Logo */}
            <Link 
                to="/" 
                data-testid="default-link" 
                className="text-xl font-bold"
            >
                MyStore
            </Link>

            {/* Nav Links */}
            <nav>
                <ul className="flex items-center gap-6">
                    <li>
                        <NavLink
                            to="/"
                            data-testid="home-link"
                            className={({ isActive }) =>
                                isActive ? "font-semibold text-primary" : "text-muted-foreground"
                            }
                        >
                            Home
                        </NavLink>
                    </li>
                    <li>
                        <NavLink
                            to="/shop"
                            data-testid="shop-link"
                            className={({ isActive }) =>
                                isActive ? "font-semibold text-primary" : "text-muted-foreground"
                            }
                        >
                            Shop
                        </NavLink>
                    </li>
                </ul>
            </nav>

            {/* Right side */}
            <div className="flex items-center gap-4">
                <Link to="/cart">
                    <Button
                        data-testid="cart-btn"
                        variant="outline" 
                        size="icon"
                    >
                        <ShoppingCart className="h-5 w-5" />
                    </Button>
                </Link>

                {auth ? (
                    <>
                        <span
                            data-testid="authenticated-email"
                            className="text-sm"
                        >
                            {auth.email}
                        </span>
                        <Button
                            data-testid="logout-btn"
                            variant="outline" 
                            onClick={logoutUser}
                        >
                            Logout
                        </Button>
                    </>
                ) : (
                    <Link to="/login">
                        <Button data-testid="login-goto-btn">Login</Button>
                    </Link>
                )}
            </div>
        </div>
    </header>
  );
}

export default Navbar;