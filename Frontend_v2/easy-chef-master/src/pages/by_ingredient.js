import React, { useEffect, useState } from "react";
import { useParams } from 'react-router-dom'

import { RecipeCard } from "../components/recipe_card";
import { getRecipesByIngredient } from "../utils/endpoints";

export const ByIngredient = () => {
    const { ingredient } = useParams();
    const [recipes, setRecipes] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        async function fetchData(){
            //fetch recipes with the ingredient
            const list = await getRecipesByIngredient(ingredient);
            setRecipes(list);
            setIsLoading(false);
        }
        fetchData();
    }, []);

    return (
        <main className="page">
            <div className="featured-recipes">
                <h3>Recipes containing {ingredient}</h3>
                {/* recipes list */}
                <div className="recipes-list">
                    {isLoading ?
                        <div className="loading"></div>
                        :
                        recipes.map((recipe, index) => <RecipeCard recipe={recipe} key={index} />)
                    }
                </div>
                {/* end of recipe list */}
            </div>

        </main>
    );
}