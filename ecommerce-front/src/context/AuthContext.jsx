import { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [auth, setAuth] = useState(null);

  useEffect(() => {
    const saved = localStorage.getItem("auth");
    if (saved) {
      setAuth(JSON.parse(saved));
    }
  }, []);

  const loginUser = (authData) => {
    localStorage.setItem("auth", JSON.stringify(authData));
    setAuth(authData);
  };

  const logoutUser = () => {
    localStorage.removeItem("auth");
    setAuth(null);
  };

  return (
    <AuthContext.Provider value={{ auth, loginUser, logoutUser }}>
      {children}
    </AuthContext.Provider>
  );
}