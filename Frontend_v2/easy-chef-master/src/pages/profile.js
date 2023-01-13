
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import chef from "../assets/chef.jpg";

import { RecipeCard } from '../components/recipe_card';
import { useAuthContext } from '../utils/auth_context';
import { getRecipesByChef } from '../utils/endpoints';

export const Profile = () => {

    const navigate = useNavigate();
    const { user, isLogged } = useAuthContext();
    const [recipes, setRecipes] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        if (!isLogged())
            navigate("/home");
        async function fetchData(){
            const list = await getRecipesByChef(user?.id);
            setRecipes(list);
            setIsLoading(false);
        }
        fetchData();
    }, []);

    return (
        <main className="page">
            <section className="about-page">
                <article>
                    <h2>Personal Chef profile</h2>
                    <h3>{user?.name}</h3>
                    <h4>{user?.email}</h4>
                    {/*<p>
            Inserire una eventuale descrizione
            Nel caso va fatto anche nel Backend per la classe User
          </p>*/}
                </article>
                <img
                    src={chef}
                    alt="Chef Image"
                    className="img about-img"
                />
            </section>
            <section className="featured-recipes">
                <h3 className="featured-title">My recipes:</h3>
                <div className="recipes-list">
                    {isLoading ?
                        <div className="loading"></div>
                        :
                        recipes.map((recipe, index) => <RecipeCard recipe={recipe} key={index} from="profile" />)
                    }
                </div >
            </section >
        </main >
    );
}