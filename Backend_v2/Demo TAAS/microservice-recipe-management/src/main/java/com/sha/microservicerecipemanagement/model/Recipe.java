package com.sha.microservicerecipemanagement.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data //for getter and setters with Lombok
@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "num_portions")
    private Integer numPortions;

    @Column(name = "cooking_time")
    private Float cookingTime;

    @Column(name = "cooking_method")
    private String cookingMethod;

    @Column(name = "ingredients_quantity")
    private Integer ingredientsQuantity;

    @Column(name = "recipe procedure")
    private String procedure;

    // è uno user, nome etc si può prendere da la. No?
    @Column(name = "chef_id")
    private Long chefId;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
//    private Ingredient ingredient;

    //private List<Ingredient> ingredients;

    // private Long idChef; <--- da capire lo scopo
    // private String chefName;
}
