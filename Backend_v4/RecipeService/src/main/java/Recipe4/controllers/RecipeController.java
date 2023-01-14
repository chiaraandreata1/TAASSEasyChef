package Recipe4.controllers;

import Recipe4.models.Recipe;
import Recipe4.repositories.RecipeRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    @Autowired
    RecipeRepository repository;

    @Autowired
    RabbitTemplate template;

    /* Per ottenere l'elenco di tutte le ricette */
    @GetMapping("/recipes/allrecipes")
    public List<Recipe> getAllRecipes() {
        System.out.println("Get all recipes...");

        List<Recipe> recipes = new ArrayList<>();
        repository.findAll().forEach(recipes::add);

        return recipes;
    }

    /*per ottenere l'elenco ricette dato l'idChef dell'utente che le ha create */
    @GetMapping(value = "/recipes/recipesbyid/{idRecipe}")
    public Recipe findById(@PathVariable Long idRecipe) {

        Recipe rec = repository.findRecipeById(idRecipe);
        return rec;
    }


    /*per ottenere l'elenco ricette dato un certo ingrediente*/
    @GetMapping(value = "/recipes/recipesbycookingmethod/{cookingMethod}")
    public List<Recipe> findByCookingMethod(@PathVariable String cookingMethod) {

        List<Recipe> customers = repository.findByCookingMethod(cookingMethod);
        return customers;
    }

    /*per ottenere l'elenco ricette dato l'idChef dell'utente che le ha create */
    @GetMapping(value = "/recipes/recipesbychef/{idChef}")
    public List<Recipe> findByIdChef(@PathVariable Long idChef) {

        List<Recipe> rec = repository.findByIdChef(idChef);
        return rec;
    }

    /*per ottenere l'elenco delle ricette dato un ingrediente*/
    @GetMapping(value = "/recipes/recipesbyingredient/{ingredient}")
    public List<Recipe> findByIngredient(@PathVariable String ingredient) {
        System.out.println("Searching recipes with ingredient = " + ingredient + "...");

        List<Recipe> recipes = getAllRecipes();
        List<Recipe> result = new ArrayList<Recipe>();
        for(Recipe r: recipes){
            List<String> ingredients = r.getIngredientsList();
            for(String i: ingredients) {
                if(i.equals(ingredient)){
                    result.add(r);
                }
            }
        }

        return result;
    }


    /* per ottenere T o F per sapere se idChef è presente alla lista dei like nella ricetta
    @GetMapping(value = "recipes/recipeIsLiked/{idRecipe}/{ChefId}")
    public boolean isRecipeLiked(@PathVariable Long idRecipe, Long ChefId) {
        System.out.println("Checking if is recipe = " + idRecipe + " Liked by "+ChefId+"...\n");

        Recipe recipe = repository.findRecipeById(idRecipe);
        boolean isLiked = recipe.getLikesList().contains(ChefId);
        return isLiked;
    }*/

    /* per ottenere numero di like di una ricetta dato il suo id
    @GetMapping(value = "recipes/recipeslikescounter/{idRecipe}")
    public Integer numberOfLikes(@PathVariable Long idRecipe) {
        System.out.println("Counting number of likes for recipe = " + idRecipe + "...\n");

        Recipe recipe = repository.findRecipeById(idRecipe);
        int counter= recipe.countLikes();

        System.out.println(counter +" likes for recipe = " + idRecipe + "...\n");
        return counter;
    }*/


    /* Per aggiungere una nuova ricetta*/
    //@CrossOrigin(methods = {RequestMethod.OPTIONS, RequestMethod.POST})
    @PostMapping(value = "/recipes/createrecipe")
    public Recipe postRecipe(@RequestBody Recipe recipe) {

        Recipe _recip = repository.save(new Recipe( recipe.getTitle(), recipe.getIdChef(), recipe.getNumPortions(), recipe.getCookingTime(),
                recipe.getCookingMethod(), recipe.getCategory(), recipe.getProcedure(), recipe.getIngredientsList(), recipe.getLikesList()));

        return _recip;
    }


    /* TODO: DA TESTARE Per cancellare una ricetta dato l'id*/
    @DeleteMapping("/recipes/deleterecipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") long id) {
        System.out.println("Delete Recipes with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Recipe has been deleted!", HttpStatus.OK);
    }

    /* Per cancellare TUTTE le ricette contenute nel db*/
    @DeleteMapping("/recipes/deleteallrecipes")
    public ResponseEntity<String> deleteAllRecipes() {
        System.out.println("Delete All Recipes...");

        repository.deleteAll();

        return new ResponseEntity<>("All recipes have been deleted!", HttpStatus.OK);
    }

    /* per modificare i parametri di una ricetta dato il suo id */
    /*Per non avere errori tutti i campi devono essere presenti nel Json */
    @PutMapping("/recipes/editrecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        System.out.println("Update Recipe with ID = " + id + "...");

        Optional<Recipe> recipeData = repository.findById(id);

        if (recipeData.isPresent()) {
            Recipe _rec = recipeData.get();
            _rec.setTitle(recipe.getTitle());
            //_rec.setIdChef(recipe.getIdChef());
            //_rec.setNumPortions(recipe.getNumPortions());
            //_rec.setCookingTime(recipe.getCookingTime());
            //_rec.setCookingMethod(recipe.getCookingMethod());
            //_rec.setCategory(recipe.getCategory());
            _rec.setProcedure(recipe.getProcedure());
            //_rec.setIngredientsList(recipe.getIngredientsList());
            //_rec.setLikesList(recipe.getLikesList());

            return new ResponseEntity<>(repository.save(_rec), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}




