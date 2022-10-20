package recipeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hybernateLazyInitializer","handler"}) //ogni volta che aggiungo una entity permette l'aggiornamento lazy
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idIngredient;
    private String nameIngredient;
    //private String quantityIngredient;

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    /*public String getQuantityIngredient() {
        return quantityIngredient;
    }

    public void setQuantityIngredient(String quantityIngredient) {
        this.quantityIngredient = quantityIngredient;
    }*/

    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

}
