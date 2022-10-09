package recipeservice.controllers;

import java.util.List;

@RestController
@RequstMapping("api/v1")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipes")   //si concatenerà api/v1/users per restituire la lista di tutte le ricette
    public List<Recipe> listAllRecipes(){
        return recipeRepository.findAll();
    }

    @GetMapping("recipebyid/{idRecipe}") // per avere l'oggetto ricetta passando l'id
    public Recipe recipeById(@PathVariable Long idRecipe){ return recipeRepository.findRecipeById(idRecipe);}

    @GetMapping("/recipesbycookingmethod/{cookingMethod}") // per avere tutte le ricette dato il metodo di cottura passato come stringa
    public List<Recipe> listRecipesByCookingMethod(@PathVariable String cookingMethod){ return recipeRepository.findAllByCookingMethod(cookingMethod); }

    @GetMapping("/recipesbyingredient/{idIngredient}") // per avere tutte le ricette con un dato ingrediente (deve essere fornito l'id dell'ingrediente)
    public List<Recipe> listRecipesByIngredient(@PathVariable Long idIngredient){return recipeRepository.findAllByIngredientContaining(idIngredient);}

    @GetMapping("/recipesbychef/{idChef}") // per avere tutte le ricette create da un utente (deve essere fornito l'id dell'utente)
    public List<Recipe> listRecipeByChef(@PathVariable Long idChef){return recipeRepository.findAllByIdChef(idChef);}

    @PostMapping(value = "/recipe/create")// si concatena api/v1/recipe/create per aggiungere una ricetta al DB
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @DeleteMapping("/recipe/delete/{id}") // per eliminare una determiata ricetta dato l'id
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") long id){
        System.out.println("Delete recipe with id = " + id + "...");
        recipeRepository.deleteById(id);
        return new ResponseEntity<>("Recipe deleted!", HttpStatus.OK);
    }
}
