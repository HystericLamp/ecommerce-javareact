import { Link, NavLink } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { ShoppingCart } from "lucide-react";
import { useContext } from "react";

import { AuthContext } from "../context/AuthContext";

function Navbar() {
  const { auth, logoutUser } = useContext(AuthContext);

  return (
    <header className="border-b bg-background sticky top-0 z-50">
        <div className="container mx-auto flex h-16 items-center justify-between px-4">
            
            {/* Logo */}
            <Link to="/" className="text-xl font-bold">
            MyStore
            </Link>

            {/* Nav Links */}
            <nav>
                <ul className="flex items-center gap-6">
                    <li>
                        <NavLink
                        to="/"
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
                    <Button variant="outline" size="icon">
                        <ShoppingCart className="h-5 w-5" />
                    </Button>
                </Link>

                {auth ? (
                    <>
                    <span className="text-sm">{auth.email}</span>
                    <Button variant="outline" onClick={logoutUser}>
                        Logout
                    </Button>
                    </>
                ) : (
                    <Link to="/login">
                    <Button>Login</Button>
                    </Link>
                )}
            </div>
        </div>
    </header>
  );
}

export default Navbar;