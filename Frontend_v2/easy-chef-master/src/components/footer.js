import { NavLink } from "react-router-dom";
import { useAuthContext } from "../utils/auth_context";

export const Footer = () => {

    const { isLogged } = useAuthContext();

    return (
        <footer className="page-footer">
            <div className="footer-links">
                <NavLink to="/home" className={`footer-link`}> home </NavLink>

                {isLogged() &&
                    <NavLink to="/add-recipe" className={`footer-link`}> add recipe </NavLink>}

                {isLogged() &&
                    <NavLink to="/add-ingredient" className={`footer-link`}> add ingredient </NavLink>}

                {isLogged() &&
                    <NavLink to="/profile" className={`footer-link`}> profile </NavLink>}
            </div>
            <p>Progetto di TAASS di Chiara Andreata</p>
            <div className="footer-text">
                <p> &#169; {new Date().getFullYear()} - All rights reserved </p>
            </div>
        </footer>
    );
}