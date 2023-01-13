import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";

import { RecipeCard } from "../components/recipe_card";
import { getAllIngredients, getAllCookingMethods, getAllRecipes } from "../utils/endpoints";

const Home = () => {
    const [isLoading, setIsLoading] = useState(true);

    const [ingredients, setIngredients] = useState([]);
    const [methods, setMethods] = useState([]);
    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
        async function fetchData() {
            // fetch ingredients
            const allIngredients = await getAllIngredients();
            setIngredients(allIngredients);

            //fetch methods
            const allCookingMethods = await getAllCookingMethods();
            setMethods(allCookingMethods);

            //fetch recipes
            const allRecipes = await getAllRecipes();
            setRecipes(allRecipes);

            setIsLoading(false);
        }
        fetchData();

    }, []);

    return (
        <main className="page">
            {/* header */}
            <header className="hero">
                <div className="hero-container">
                    <div className="hero-text">
                        <h2>EASY TO MAKE RECIPES</h2>
                        <h3>THAT TAKE STRESS OUT OF COOKING</h3>
                    </div>
                </div>
            </header>

            {/* end of header  */}
            <section className="ingredients-container">
                {/* <section className="ingredients-container"> */}
                <div className="tags-container">
                    {/* tag container */}
                    <h4>Search recipe by ingredients</h4>
                    <div className="ingredients-list">
                        {
                            isLoading ?
                                <div className="loading"></div>
                                :
                                ingredients.map((ingredient, index) => {
                                    return (
                                        <NavLink to={`/by-ingredient/${ingredient.name}`} key={index}>
                                            {ingredient.name}
                                        </NavLink>
                                    );
                                })
                        }
                    </div>
                </div>
                {/* end of tag container */}
            </section>

            {/* tag container */}
            <section className="recipes-container">
                <div className="tags-container">
                    <h4>Search recipe by cooking method</h4>
                    <div className="tags-list">
                        {
                            isLoading ?
                                <div className="loading"></div>
                                :
                                methods.map((method, index) => {
                                    return (
                                        <NavLink to={`/by-method/${method}`} key={index}>
                                            {method}
                                        </NavLink>
                                    );
                                })
                        }
                    </div>
                </div>
                {/* end of tag container */}
                {/* recipes list */}
                <div className="recipes-list">
                    {
                        isLoading ?
                            <div className="loading"></div>
                            :
                            recipes.map((recipe, index) => <RecipeCard recipe={recipe} key={index} from="home" />)
                    }
                </div>
                {/* end of recipes list */}
            </section>
        </main>
    );
};

export default Home;