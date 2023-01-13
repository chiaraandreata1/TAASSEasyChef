import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from 'react-router-dom'
import { useAuthContext } from "../utils/auth_context";
import { getRecipeById, getChefById, msgToRecipe, getAllRecipes, putRecipe } from "../utils/endpoints";
import $ from "jquery";

import chefHat from "../assets/chef-hat.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClock, faEdit } from "@fortawesome/free-regular-svg-icons";
import { faThermometerHalf, faUserFriends, faCheck } from "@fortawesome/free-solid-svg-icons";

export const Recipe = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const { user, isLogged } = useAuthContext();

    const [isLoading, setIsLoading] = useState(true);
    const [recipe, setRecipe] = useState(null);
    const [chef, setChef] = useState(null);
    const [isEdit, setIsEdit] = useState(false);

    useEffect(() => {
        async function fetchData() {
            const ID = parseInt(id);
            if(isNaN(ID)){
                //redirect to 404 page
                navigate('/page-not-found');
                return;
            }

            //fetch recipe infos
            const r = await getRecipeById(ID);
            console.log(r);

            // if recipe is not found
            if (r === undefined) {
                //redirect to 404 page
                navigate('/page-not-found');
                return;
            }

            setRecipe(r);

            //fetch chef infos
            const chef = await getChefById(r.idChef);
            setChef(chef);

            setIsLoading(false);
        }
        fetchData();
    }, []);

    const isLiked = () => recipe.likesList.includes(user?.id);

    const isOwner = () => (recipe.idChef == user?.id)

    const handleLike = () => {
        const body = {
            "idChef": user?.id,
            "idRecipe": recipe.id,
            "enableLike": !isLiked(), //if user already liked the recipe, disable like
        };

        async function postData(){
            await msgToRecipe(body);
        }
        postData();

        window.location.reload()
    }

    const editTitle = () => {
        $(".recipe-info #title").attr('contenteditable','true');
        $(".recipe-info #title").focus();
        document.addEventListener('keydown', (event) => {
            if (event.key === 'Enter') {
                event.preventDefault();
                confirmEditTitle();
                $(".recipe-info #title").attr('contenteditable','false');
            }
        });
    }

    const editProcedure = () => {
        $(".recipe-content #procedure").attr("readonly", false);
        $(".recipe-content #procedure").focus();
        setIsEdit(true);
    }

    const confirmEditTitle = () => {
        async function postData(){
            const body = {
                title: $(".recipe-info #title").text(),
                procedure: recipe.procedure
            }
            await putRecipe(recipe.id, body);
        }
        postData();
    }

    const confirmEditProcedure = () => {
        console.log($(".recipe-content #procedure").html($(".recipe-content #procedure").val().replace(/\r?\n/g, '<br />')));
        async function postData(){
            const body = {
                title: recipe.title,
                procedure: $(".recipe-content #procedure").text()
            }
            await putRecipe(recipe.id, body);
        }
        postData();
        $(".recipe-content #procedure").attr("readonly", true);
        setIsEdit(false);
    }

    return (
        <main className="page">
            {isLoading ?
                <div className="loading"></div>
                :
                <div className="recipe-page">
                    <section className="recipe-hero">
                        <img
                            src={`../images/recipes/${recipe.category}.jpg`}
                            className="img recipe-hero-img"
                            alt={recipe.category}
                        />

                        <article className="recipe-info">
                            <div style={{"display": "flex"}}>
                                {isOwner() && <FontAwesomeIcon style={{"marginRight": "1em"}} icon={faEdit} size={"xl"} onClick={editTitle}/>}
                                <h2 id="title">{recipe.title}</h2>
                            </div>
                            <div className="recipe-chef">
                                <img src={chefHat} alt="Chef Hat" />
                                <h4> {chef.username} </h4>
                            </div>
                            <div className="recipe-icons">
                                <article>
                                    <FontAwesomeIcon icon={faClock} />
                                    <h5>cooking time</h5>
                                    <p>{recipe.cookingTime} min.</p>
                                </article>
                                <article>
                                    <FontAwesomeIcon icon={faThermometerHalf} />
                                    <h5>Cooking method</h5>
                                    <p>{recipe.cookingMethod}</p>
                                </article>
                                <article>
                                    <FontAwesomeIcon icon={faUserFriends} />
                                    <h5>portions</h5>
                                    <p>{recipe.numPortions}</p>
                                </article>
                            </div>
                        </article>
                    </section>
                    {/* content */}
                    <section className="recipe-content">
                        <article>
                            <div style={{"display": "flex"}}>
                                {!isEdit ?
                                isOwner() && <FontAwesomeIcon style={{"marginRight": "1em"}} icon={faEdit} size={"xl"} onClick={editProcedure}/>
                                :
                                isOwner() && <FontAwesomeIcon style={{"marginRight": "1em"}} icon={faCheck} size={"xl"} onClick={confirmEditProcedure}/>
                                }
                                <h3>instructions</h3>
                            </div>
                            <textarea id="procedure" readOnly={true} style={{"width": "100%", "height": "220px", "resize": "none"}}>
                                {recipe.procedure.replaceAll("<br />", "\n")}
                            </textarea>
                            <br />
                            {isLogged() &&
                                (isLiked() ?
                                    <button className="btn" onClick={handleLike}>Unlike Recipe</button>
                                    :
                                    <button className="btn" onClick={handleLike}>Like Recipe</button>)
                            }
                        </article>
                        <article className="second-column">
                            <div>
                                <h3>ingredients</h3>
                                {
                                    recipe.ingredientsList.map((ingredient, index) => <p className="single-ingredient" key={index}>{ingredient}</p>)
                                }
                            </div>
                        </article>
                    </section>
                </div>
            }
        </main>
    );
};