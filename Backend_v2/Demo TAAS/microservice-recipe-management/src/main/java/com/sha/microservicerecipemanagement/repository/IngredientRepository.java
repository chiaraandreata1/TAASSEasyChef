package com.sha.microservicerecipemanagement.repository;

import com.sha.microservicerecipemanagement.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // find by ingredient name
    Ingredient findByIngredientName(String ingredientName);

    // find by ingredient id
    Ingredient findIngredientById(Long id);
}
