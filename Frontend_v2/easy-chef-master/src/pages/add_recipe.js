import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import main from "../assets/main.jpeg";
import { useAuthContext } from "../utils/auth_context";
import { createRecipe, getAllCookingMethods, getAllIngredients, getAllRecipes } from "../utils/endpoints";
import $ from "jquery";

export const AddRecipe = () => {

    const navigate = useNavigate();
    const { user, isLogged } = useAuthContext();

    const [isLoading, setIsLoading] = useState(true);
    const [ingredients, setIngredients] = useState([]);
    const [methods, setMethods] = useState([]);

    useEffect(() => {
        if (!isLogged())
            navigate("/home");

        async function fetchData(){
            //fetch ingredients
            const ingredientsList = await getAllIngredients();
            setIngredients(ingredientsList);

            //fetch methods
            const methodsList = await getAllCookingMethods();
            setMethods(methodsList);

            setIsLoading(false);
        }
        fetchData();
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData(e.currentTarget);
        const data = {
            "title": formData.get("name"),
            "idChef": user?.id,
            "numPortions": parseInt(formData.get("portions")),
            "cookingTime": parseInt(formData.get("cookingTime")),
            "cookingMethod": formData.get("cookingMethod"),
            "category": formData.get("category"),
            "ingredientsList": formData.getAll("ingredient"),
            "procedure": formData.get("procedure")
        }

        let title = "Recipe added successfully!";
        let description = "The given recipe has been added to the database.";
        let alertClass = "alert-success";

        // check if all fields are filled
        if (Object.values(data).some((value) => value === null || value.length === 0)) {
            title = "Recipe not added!";
            description = "Please fill all the fields.";
            alertClass = "alert-danger";
        } else {
            async function createData(){
                data.likesList= [];
                //create recipe
                await createRecipe(data);
            }
            createData();
        }

        $(".alert").removeClass("alert-success");
        $(".alert").removeClass("alert-danger");
        $(".alert").addClass(alertClass);
        $(".alert #title").text(title);
        $(".alert #description").text(description);
        $(".alert").fadeIn(250);
    }

    return (
        <main className="page">
            <section className="contact-container">
                <article className="contact-info">
                    <h3>Create New Recipe</h3>
                    <p>
                        Insert here your newest recipe and share it with EasyChef community!
                    </p>
                    <img
                        src={main}
                        alt="RecipeImage"
                        className="img about-img"
                    />
                </article>
                <article>
                    <form className="form contact-form" onSubmit={handleSubmit}>
                        <div className="form-row">
                            <h5>Recipe Title</h5>
                            <input type="text" name="name" id="title" className="form-input" />
                        </div>
                        <div>
                            <h5>Chef Name:</h5> <h4 id="chefName">{user?.name}</h4>
                        </div>
                        <div className="form-row">
                            <h5>Portions:</h5>
                            <input type="number" placeholder="1" min="1" name="portions" id="portions" className="form-input" />
                        </div>
                        <div className="form-row">
                            <h5>Cooking Time (in minutes):</h5>
                            <input type="number" placeholder="0" min="0" name="cookingTime" id="cookingTime" className="form-input" />
                        </div>
                        <div>
                            <h5>Cooking Method</h5>
                            {
                                isLoading ?
                                    <div className="loading"></div>
                                    :
                                    <select className="form-input" name="cookingMethod">
                                        {methods.map((method, index) => (<option key={index} value={method}>{method}</option>))}
                                    </select>
                            }
                        </div>
                        <br />
                        <div>
                            <h5>Recipe Category</h5>
                            <div className="form-choice">
                                <div><input type="radio" id="pasta" name="category" value="pasta" /> <label htmlFor="pasta"> Pasta </label></div>
                                <div><input type="radio" id="soup" name="category" value="soup" /> <label htmlFor="soup"> Soup </label></div>
                                <div><input type="radio" id="fish" name="category" value="fish" /> <label htmlFor="fish"> Fish & Seafood </label></div>
                                <div><input type="radio" id="meat" name="category" value="meat" /> <label htmlFor="meat"> Meat </label></div>
                                <div><input type="radio" id="vegan" name="category" value="vegan" /> <label htmlFor="vegan"> Vegan </label></div>
                                <div><input type="radio" id="ethnic" name="category" value="ethnic" /> <label htmlFor="ethnic"> Ethnic </label></div>
                                <div><input type="radio" id="sandwich" name="category" value="sandwich" /> <label htmlFor="sandwich"> Sandwich </label></div>
                                <div><input type="radio" id="dessert" name="category" value="dessert" /> <label htmlFor="dessert"> Dessert </label></div>
                                <div><input type="radio" id="other" name="category" value="other" /> <label htmlFor="other"> Other </label></div>
                            </div>
                        </div>
                        <br />
                        <div>
                            <h5>Select Ingredients</h5>
                            {
                                isLoading ?
                                    <div className="loading"></div>
                                    :
                                    <div className="form-choice">
                                        {
                                            ingredients.map((ingredient, index) => (
                                                <div key={index}>
                                                    <input style={{ marginRight: ".3em" }} type="checkbox" id={ingredient.name} name="ingredient" value={ingredient.name} />
                                                    <label htmlFor={ingredient.name}>{ingredient.name}</label>
                                                </div>
                                            ))
                                        }
                                    </div>
                            }
                            <br />
                            <NavLink to="/add-ingredient">
                                <FontAwesomeIcon icon={faPlus} /> new ingredient
                            </NavLink>
                        </div>
                        <br />
                        <div className="form-row">
                            <h5>Insert Preparation Procedure</h5>
                            <textarea name="procedure" id="procedure" className="form-textarea"></textarea>
                        </div>
                        <button type="submit" className="btn btn-block">
                            submit
                        </button>
                    </form>
                </article>
            </section>

        </main>
    );
}