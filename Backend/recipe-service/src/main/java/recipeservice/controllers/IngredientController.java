package recipeservice.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recipeservice.models.Ingredient;
import recipeservice.repositories.IngredientRepository;

@RestController
@RequestMapping("api/v1")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/ingredients")   //si concatena api/v1/ingredients per restituire la lista di tutti gli ingredienti
    public List<Ingredient> listAllIngredients(){
        return ingredientRepository.findAll();
    }

    @GetMapping("/ingredientbyname/{ingredientName}") // per avere un ingrediente dato il nome del'ingrediente passato come stringa
    public Ingredient ingredientByName(@PathVariable String ingredientName){ return ingredientRepository.findByNameIngredient(ingredientName); }

    @GetMapping("ingredientbyid/{idIngredient}") // per avere l'oggetto ingrediente inserendo l'id
    public Ingredient ingredientById(@PathVariable Long idIngredient){ return ingredientRepository.findIngredientById(idIngredient);}

    @PostMapping(value = "/ingredient/create")// si concatena api/v1/ingredient/create per aggiungere un nuovo ingrediente al DB
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    // Non esiste un metodo per eliminare ingredienti, scelta di design per mantenere la consistenza nel DB se eliminate ricette
}
