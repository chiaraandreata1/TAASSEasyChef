package Recipe4.repositories;

import Recipe4.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByCookingMethod(String cookingMethod);
    List<Recipe> findByIdChef(Long idChef);
    Recipe findRecipeById(Long idRecipe);

    /*List<Recipe> isRecipeLiked(Long idRecipe, Long ChefId);
    Integer numberOfLikes( Long idRecipe);*/

    /*
    List<Recipe> findAllByIdChef(Long idChef);
    List<Recipe> findAllByCookingMethod(String cookingMethod);
   */
}
