package recipeservice.repositories;

import recipeservice.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByNameIngredient(String nameIngredient);
    Ingredient findIngredientById(Long idIngredient);
}
