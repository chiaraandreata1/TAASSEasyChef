package com.sha.microservicerecipemanagement.service;

import com.sha.microservicerecipemanagement.model.Recipe;
import com.sha.microservicerecipemanagement.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> allRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> findAllByIngredientId(Long ingredientId){
        return recipeRepository.findAllByIngredientId(ingredientId);
    }

    @Override
    public Recipe findRecipeById(Long recipeId){
        if(recipeId != null) {
            return recipeRepository.findRecipeById(recipeId);
        }
        return null;
    }

    @Override
    public List<Recipe> findAllByAuthorId(Long chefId){
        return recipeRepository.findAllByAuthorId(chefId);
    }
    // mettere i controlli sui null
    @Override
    public List<Recipe> findAllByCookingMethod(String cookingMethod){
        return recipeRepository.findAllByCookingMethod(cookingMethod);
    }

}
