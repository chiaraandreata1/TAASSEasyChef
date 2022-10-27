package com.sha.microservicerecipemanagement.service;

import com.sha.microservicerecipemanagement.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> allRecipes();
    public List<Recipe> findAllByIngredientId(Long ingredientId);

    public Recipe findRecipeById(Long recipeId);

    public List<Recipe> findAllByAuthorId(Long chefId);

    public List<Recipe> findAllByCookingMethod(String cookingMethod);
}
