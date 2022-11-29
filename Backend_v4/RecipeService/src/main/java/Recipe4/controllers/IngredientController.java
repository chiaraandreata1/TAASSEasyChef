package Recipe4.controllers;


import Recipe4.models.Ingredient;
import Recipe4.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class IngredientController {

    @Autowired
    IngredientRepository repository;

    /* Per ottenere l'elenco di tutti gli ingredienti */
    @GetMapping("/ingredients/allingredients")
    public List<Ingredient> getAllIngredients() {
        System.out.println("Get all ingredients...");

        List<Ingredient> ingredients = new ArrayList<>();
        repository.findAll().forEach(ingredients::add);

        return ingredients;
    }

    /*TODO: da capire come fare
     Per avere un ingrediente passando il suo nome come stringa*/
    @GetMapping(value = "ingredients/ingredientbyname/{name}")
    public Optional<Ingredient> findByName(@PathVariable String name) {

        Optional<Ingredient> ing = repository.findByName(name);
        return ing;
    }

    /* TODO: da capire come fare
     Per avere un ingrediente fornendo il suo id */
    /*@GetMapping("ingredientbyid/{idIngredient}")
    public Ingredient ingredientById(@PathVariable Long idIngredient){
        return IngredientRepository.findIngredientById(idIngredient);
    }*/

    /* Per creare un nuovo ingrediente*/
    @PostMapping(value = "/ingredients/createingredient")
    public Ingredient postIngredient(@RequestBody Ingredient ingredient) {

        Ingredient _ingred = repository.save(new Ingredient(ingredient.getName()));

        return _ingred;
    }

   /* TODO: da aggiustare
     Per eliminare un ingrediente dato il suo Id */
    /*@DeleteMapping("/ingredients/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("id") long id) {
        System.out.println("Delete Ingredient with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Ingredient has been deleted!", HttpStatus.OK);
    }*/

    /* Per eliminare TUTTI gli ingredienti */
    @DeleteMapping("/ingredients/deleteallingredients")
    public ResponseEntity<String> deleteAllIngredients() {
        System.out.println("Delete All Ingredients...");

        repository.deleteAll();

        return new ResponseEntity<>("All Ingredients have been deleted!", HttpStatus.OK);
    }

}
