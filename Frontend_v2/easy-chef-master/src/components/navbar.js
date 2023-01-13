import { faBars } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";

import logo from "../assets/logo.jpg";
import { useAuthContext } from "../utils/auth_context";

export const Navbar = () => {

    const { setUser, isLogged } = useAuthContext();

    const logout = () => {
        setUser(null);
    }

    return (
        <nav className="navbar">
            <div className="nav-center">
                <div className="nav-header">
                    <NavLink to="/home" className="nav-logo">
                        <img src={logo} alt="logo" />
                    </NavLink>
                    <button className="nav-btn btn">
                        <FontAwesomeIcon icon={faBars} size={"2x"} />
                    </button>
                </div>
                <div className="nav-links">
                    <NavLink to="/home" className={`nav-link`}> home </NavLink>

                    {isLogged() &&
                        <NavLink to="/add-recipe" className={`nav-link`}> add recipe </NavLink>}

                    {isLogged() &&
                        <NavLink to="/add-ingredient" className={`nav-link`}> add ingredient </NavLink>}

                    {isLogged() &&
                        <NavLink to="/profile" className={`nav-link`}> profile </NavLink>}
                    {/* <a href="add-category" className="nav-link"> add category </a> TODO: vedere se ha senso tenerla */}

                    <div className="nav-link contact-link">
                        {!isLogged() ?
                            <NavLink to="/login" className="btn btn-login"> Login </NavLink>
                            :
                            <a href="/home" onClick={() => logout()} className="btn btn-logout"> Logout </a>
                        }
                    </div>
                </div>
            </div>
        </nav>
    );
}