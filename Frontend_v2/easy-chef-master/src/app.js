import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Footer } from "./components/footer";
import { Navbar } from "./components/navbar";
import { PageNotFound } from "./pages/page_not_found";
import { ByIngredient } from "./pages/by_ingredient";
import { ByMethod } from "./pages/by_method";
import Home from "./pages/home";
import { Profile } from "./pages/profile";
import { Recipe } from "./pages/recipe";
import { AddRecipe } from "./pages/add_recipe";
import { useEffect, useState } from "react";
import { Login } from "./pages/login";
import { AuthContext } from "./utils/auth_context";
import { AddIngredient } from "./pages/add_ingredient";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClose } from "@fortawesome/free-solid-svg-icons";
import $ from "jquery";

const App = () => {

    const [user, setUser] = useState({});

    const isLogged = () => {
        return user !== null && Object.keys(user).length !== 0;
    }

    const closeAlert = () => {
        $(".alert").fadeOut(500);
    }

    useEffect(() => {
        // Persist data in localStorage to avoid losing it on page refresh
        const localStorageUser = JSON.parse(window.localStorage.getItem("user"));
        if (localStorageUser === null || Object.keys(localStorageUser).length !== 0)
            setUser(localStorageUser);
    }, []);

    useEffect(() => {
        // update localStorage when user changes
        window.localStorage.setItem("user", JSON.stringify(user));
    }, [user]);

    return (
        <AuthContext.Provider value={{
            user: user,
            setUser: setUser,
            isLogged: isLogged
        }}>
            <div className="alert">
                <FontAwesomeIcon icon={faClose} className="alert-close" onClick={() => closeAlert()} />
                <h3 id="title"></h3>
                <p id="description"></p>
            </div>
            <div>
                <Router>
                    <Navbar />
                    <Routes>
                        <Route path="/" exact element={<Home />} />
                        <Route path="/home" exact element={<Home />} />
                        <Route path="/by-ingredient/:ingredient" exact element={<ByIngredient />} />
                        <Route path="/by-method/:method" exact element={<ByMethod />} />
                        <Route path="/recipe/:id" exact element={<Recipe />} />
                        <Route path="/profile" exact element={<Profile />} />
                        <Route path="/add-recipe" exact element={<AddRecipe />} />
                        <Route path="/add-ingredient" exact element={<AddIngredient />} />

                        <Route path="/login" exact element={<Login />} />

                        <Route path="*" exact element={<PageNotFound />}
                        />
                    </Routes>
                    <Footer />
                </Router>
            </div>
        </AuthContext.Provider>
    );
}

export default App;
