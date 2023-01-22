import axios from 'axios';

const baseUrl = (port = 8080) => `http://localhost:${port}/api/v1`;

const createChefAPI = `${baseUrl(8082)}/chefs/createchef`;
const updateChefAPI = `${baseUrl(8082)}/chefs/editchef`;

const createIngredientAPI = `${baseUrl(8081)}/ingredients/createingredient`;
const getAllIngredientsAPI = `${baseUrl(8081)}/ingredients/allingredients`;

const createRecipeAPI = `${baseUrl(8081)}/recipes/createrecipe`;
const getRecipeByIdAPI = `${baseUrl(8081)}/recipes/recipesbyid`
const getAllRecipesAPI = `${baseUrl()}/recipes/allrecipes`;
const getRecipesByIngredientAPI = `${baseUrl()}/recipes/recipesbyingredient`;
const getRecipesByCookingMethodAPI = `${baseUrl()}/recipes/recipesbycookingmethod`; // eg. `${getRecipesByCookingMethodAPI}/oven`
const getRecipesByChefAPI = `${baseUrl()}/recipes/recipesbychef`;
const delRecipeAPI = `${baseUrl(8081)}/recipes/deleterecipe`;
const putRecipeAPI = `${baseUrl(8081)}/recipes/editrecipe`;

const getChefAPI = `${baseUrl()}/chefs/findById`;
const getChefByMailAPI = `${baseUrl()}/chefs/findByMail`;
const msgToRecipeAPI = `${baseUrl(8082)}/chefs/msgToRecipe`;

const get = async (url) => {
    try{
        const response = await fetch(url, {
            method: "GET",
        });
        const data = await response.json();
        return data;
    }catch(e){
        console.log(e);
        return [];
    }
}

const post = async (url, body) => {
    try{
        console.log("BODY: ", body);
        const response = await axios.post(url, body, {
            headers: {
                "Content-Type": "application/json",
            }
        });
        const { data } = response.data;
        return data;
    }catch(e){
        console.log(e);
        return null;
    }
}

const del = async (url) => {
    try{
        const response = await fetch(url, {
            method: "DELETE",
        });
        return response;
    }catch(e){
        console.log(e);
        return null;
    }
}

const put = async (url, body) => {
    try{
        console.log("URL: ", url);
        console.log("BODY: ", body);
        const response = await axios.put(url, body, {
            headers: {
                "Content-Type": "application/json",
            }
        });
        const { data } = response.data;
        return data;
    }catch(e){
        console.log(e);
        return null;
    }
}

export const createChef = (username,mail) => post(createChefAPI, username, mail);
export const updateChef = (id, newUsername) => put(`${updateChefAPI}/${id}`, newUsername);

export const createIngredient = (ingredient) => post(createIngredientAPI, ingredient);
export const getAllIngredients = () => get(getAllIngredientsAPI);
export const getAllCookingMethods = () => ["oven","cooker","fryer","grill","no-cooking"];

export const createRecipe = (recipe) => post(createRecipeAPI, recipe);
export const getAllRecipes = () => get(getAllRecipesAPI);
export const getRecipeById = (id) => get(`${getRecipeByIdAPI}/${id}`);
export const getRecipesByIngredient = (ingredient) => get(`${getRecipesByIngredientAPI}/${ingredient}`);
export const getRecipesByCookingMethod = (method) => get(`${getRecipesByCookingMethodAPI}/${method}`);
export const getRecipesByChef = (id) => get(`${getRecipesByChefAPI}/${id}`);
export const delRecipe = (id) => del(`${delRecipeAPI}/${id}`);
export const putRecipe = (id, newRecipe) => put(`${putRecipeAPI}/${id}`, newRecipe);

export const getChefById = (id) => get(`${getChefAPI}/${id}`);
export const getChefByMail = (mail) => get(`${getChefByMailAPI}/${mail}`);
export const msgToRecipe = (msg) => post(msgToRecipeAPI, msg);