package Recipe4.repositories;

import Recipe4.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    Optional<Ingredient> findByName(String name);
}
