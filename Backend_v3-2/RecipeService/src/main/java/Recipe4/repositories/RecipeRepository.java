package Recipe4.repositories;

import Recipe4.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByCookingMethod(String cookingMethod);
    List<Recipe> findByIdChef(Long idChef);
    /*  List<Recipe> findByCookingMethod(String cookingmethod);*/
    /*Recipe findRecipeById(Long idRecipe);
    List<Recipe> findAllByIdChef(Long idChef);
    List<Recipe> findAllByCookingMethod(String cookingMethod);
    List<Recipe> findAllByIngredientContaining(Long idIngredient);*/
}
