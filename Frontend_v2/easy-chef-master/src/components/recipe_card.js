import { faStar as faStarOutline } from "@fortawesome/free-regular-svg-icons";
import { faStar as faStarSolid } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { useAuthContext } from "../utils/auth_context";
import { delRecipe, msgToRecipe } from "../utils/endpoints";

export const RecipeCard = ({ recipe, from = null }) => {

    const navigate = useNavigate();
    const { user } = useAuthContext();

    const {
        id,
        title,
        numPortions,
        cookingTime,
        category,
        likesList
    } = recipe;

    const deleteRecipe = () => {
        delRecipe(id);
        //update recipe state
        navigate("/home");
    }

    const handleLike = () => {
        const body = {
            idChef: user?.id,
            idRecipe: id,
            enableLike: !likesList.includes(user?.id), //if user already liked the recipe, disable like
        };

        msgToRecipe(body);

        //update recipe state
        navigate("/home");
    }

    return (
        <>
            <div className="recipe">
                <NavLink to={`/recipe/${id}`} >
                    <img
                        src={`../images/recipes/${category}.jpg`}
                        className="img recipe-img"
                        alt={title}
                    />
                    <h5>{title}</h5>
                    <p>Portions : {numPortions} | Cook : {cookingTime} min</p>
                </NavLink>
                {(from !== null && from === "profile") && (
                    <>
                        <br />
                        <button className="btn deleterecipe" onClick={deleteRecipe}>Delete Recipe</button>
                    </>
                )}
                {(from !== null && from === "home") && (
                    <h5 onClick={handleLike}>
                        {likesList.includes(user?.id) ?
                            <FontAwesomeIcon icon={faStarSolid} style={{ marginRight: ".5em" }} />
                            :
                            <FontAwesomeIcon icon={faStarOutline} style={{ marginRight: ".5em" }} />
                        }
                        {likesList.length}
                    </h5>
                )}
            </div>
        </>
    );
}