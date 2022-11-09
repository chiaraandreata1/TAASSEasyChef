package com.sha.microservicerecipemanagement.repository;

import com.sha.microservicerecipemanagement.model.Ingredient;
import com.sha.microservicerecipemanagement.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // finding recipes by ingredient
    List<Recipe> findAllByIngredientId(Long id);

    // finding recipes by author
    List<Recipe> findAllByAuthorId(Long chefId);

    // finding recipe by id
    Recipe findRecipeById(Long id);

    // find by cooking method
    List<Recipe> findAllByCookingMethod(String cookingMethod);

}
