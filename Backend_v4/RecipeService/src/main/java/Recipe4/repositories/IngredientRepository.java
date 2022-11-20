package Recipe4.repositories;

import Recipe4.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    /*static Ingredient findByNameIngredient(String nameIngredient);
    static Ingredient findIngredientById(Long idIngredient);*/
}
