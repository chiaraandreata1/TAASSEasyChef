package Recipe4.controllers;


import Recipe4.models.Ingredient;
import Recipe4.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


    /*Per avere un ingrediente passando il suo nome come stringa*/
    @GetMapping(value = "ingredients/name/{name}")
    public List<Ingredient> findByName(@PathVariable String name) {

        List<Ingredient> customers = repository.findByName(name);
        return customers;
    }

    /* Per creare un nuovo ingrediente*/
    @PostMapping(value = "/ingredients/createingredient")
    public Ingredient postIngredient(@RequestBody Ingredient ingredient) {

        Ingredient _ingred = repository.save(new Ingredient(ingredient.getName()));

        return _ingred;
    }

   /* Per eliminare un ingrediente dato il suo nome */
    @DeleteMapping("/ingredients/deletebyname/{name}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("name") String name) {
        System.out.println("Delete Ingredient = " + name + "...");

        repository.deleteById(name);

        return new ResponseEntity<>("Ingredient has been deleted!", HttpStatus.OK);
    }

    /* Per eliminare TUTTI gli ingredienti */
    @DeleteMapping("/ingredients/deleteallingredients")
    public ResponseEntity<String> deleteAllIngredients() {
        System.out.println("Delete All Ingredients...");

        repository.deleteAll();

        return new ResponseEntity<>("All Ingredients have been deleted!", HttpStatus.OK);
    }

}
