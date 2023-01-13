import React, { useEffect, useState } from "react";
import { useParams } from 'react-router-dom'
import { RecipeCard } from "../components/recipe_card";
import { getRecipesByCookingMethod } from "../utils/endpoints";

export const ByMethod = () => {
    const { method } = useParams();
    const [recipes, setRecipes] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        async function fetchData(){
            //fetch recipes with the method
            const list = await getRecipesByCookingMethod(method);
            setRecipes(list);
            setIsLoading(false);
        }
        fetchData();
    }, []);

    return (
        <main class="page">
            <div class="featured-recipes">
                <h3>Cooking Method: {method}</h3>
                {/* recipes list */}
                <div class="recipes-list">
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