package recipeservice.repositories;

import recipeservice.models.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findRecipeById(Long idRecipe);
    List<Recipe> findAllByIdChef(Long idChef);
    List<Recipe> findAllByCookingMethod(String cookingMethod);
    List<Recipe> findAllByIngredientContaining(Long idIngredient);
}
