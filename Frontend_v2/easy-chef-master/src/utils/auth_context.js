import { createContext, useContext } from "react";

export const AuthContext = createContext({
    user: {},
    setUser: (data) => { },
    isLogged: () => { }
});

export const useAuthContext = () => useContext(AuthContext);