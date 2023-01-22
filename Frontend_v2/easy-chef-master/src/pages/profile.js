
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import chef from "../assets/chef.jpg";

import { RecipeCard } from '../components/recipe_card';
import { useAuthContext } from '../utils/auth_context';
import { getRecipesByChef, updateChef } from '../utils/endpoints';
import $ from "jquery";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit } from "@fortawesome/free-regular-svg-icons";

export const Profile = () => {

    const navigate = useNavigate();
    const { user, isLogged, setUser } = useAuthContext();
    const [recipes, setRecipes] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        async function fetchData(){
            const list = await getRecipesByChef(user?.id);
            setRecipes(list);
            setIsLoading(false);
        }
        if(user?.id !== undefined){
            fetchData();
        }
    }, [user]);
    
    const editName = () => {
        $(".about-page #name").attr('contenteditable','true');
        $(".about-page #name").focus();
        document.addEventListener('keydown', (event) => {
            if (event.key === 'Enter') {
                event.preventDefault();
                confirmEditName();
                $(".about-page #name").attr('contenteditable','false');
            }
        });
    }

    const confirmEditName = () => {
        console.log("USER ID: ", user?.id);
        async function postData(){
            const body = {
                username: $(".about-page #name").text().trim(),
                mail: ""
            }
            await updateChef(user?.id, body);
        }
        postData();
        setUser({
            id: user?.id,
            name: $(".about-page #name").text().trim(),
            email: user?.email
        });
    }
    
    return (
        <main className="page">
            <section className="about-page">
                <article>
                    <h2>Personal Chef profile</h2>
                    <div style={{"display": "flex"}}>
                        {<FontAwesomeIcon style={{"marginRight": "1em"}} icon={faEdit} size={"xl"} onClick={editName}/>}
                        <h3 id="name">{user?.name}</h3>
                    </div>
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