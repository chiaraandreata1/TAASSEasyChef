import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from "../utils/auth_context";

export const Login = () => {

    const navigate = useNavigate();
    const { user, setUser, isLogged } = useAuthContext();

    useEffect(() => {
        if (isLogged())
            navigate("/home");
    }, []);

    const login = () => {
        setUser({
            id: 3,
            name: "Paolo Rossi",
            email: "paolo@gmail.com"
        });
    }

    return (
        <main className="page">
            <h1>{user?.name}</h1>
            <div className="login">
                <h3>Login</h3>
                <form>
                    <label htmlFor="email">Email</label>
                    <input type="email" name="email" id="email" />
                    <label htmlFor="password">Password</label>
                    <input type="password" name="password" id="password" />
                    <button type="button" onClick={() => login()}>Login</button>
                </form>
            </div>
        </main>
    );
}