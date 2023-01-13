import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from "../utils/auth_context";
import $ from "jquery";
import { createIngredient, getAllIngredients } from "../utils/endpoints";

export const AddIngredient = () => {

    const navigate = useNavigate();
    const { user, isLogged } = useAuthContext();

    useEffect(() => {
        if (!isLogged())
            navigate("/home");
    }, []);


    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData(e.currentTarget);
        const nameIngredient = formData.get("ingredientname");

        let title = "Ingredient added successfully!";
        let description = "The given ingredient has been added to the database.";
        let alertClass = "alert-success";

        console.log(nameIngredient);

        // check if all fields are filled
        if (nameIngredient === null || nameIngredient.length === 0) {
            title = "Ingredient not added!";
            description = "Please fill all the fields.";
            alertClass = "alert-danger";
        } else {
            const name = nameIngredient.toLowerCase();

            async function createData(){
                //create ingredient
                await createIngredient({"name": name});
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
                    <h3>Insert new ingredient</h3>
                    <p>
                        If not in the list please add
                    </p>
                </article>
                <article>
                    <form className="form contact-form" onSubmit={handleSubmit}>
                        <div className="form-row">
                            <h5>Ingredient name</h5>
                            <input type="text" name="ingredientname" id="title" className="form-input" />
                        </div>


                        <br />

                        {/* prima di fare SUBMIT mettere il nome dell'ingrediente in minuscolo*/}
                        {/*- riportare alla creazione della ricetta */}
                        <button type="submit" className="btn btn-block">
                            submit
                        </button>
                    </form>
                </article>
            </section>

        </main >
    );
}