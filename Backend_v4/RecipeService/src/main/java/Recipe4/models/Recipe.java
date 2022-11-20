package Recipe4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "idChef")
    private Long idChef;

    @Column(name = "numPortions")
    private Integer numPortions;

    @Column(name = "cookingTime")
    private Integer cookingTime;

    @Column(name = "cookingMethod")
    private String cookingMethod;

    @Column(name = "category")
    private String category;

    @Column(name = "procedure")
    private String procedure;

    @Column(name = "ingredientsList")
    private List<Ingredient> ingredientsList;

    public Recipe() {
    }

    public Recipe(String title, Long idChef, Integer numPortions, Integer cookingTime, String cookingMethod, String category, String procedure, List<Ingredient> ingredientsList) {
        this.title = title;
        this.idChef = idChef;
        this.numPortions = numPortions;
        this.cookingTime = cookingTime;
        this.cookingMethod = cookingMethod;
        this.category = category;
        this.procedure = procedure;
        this.ingredientsList = ingredientsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public Integer getNumPortions() {
        return numPortions;
    }

    public void setNumPortions(Integer numPortions) {
        this.numPortions = numPortions;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
