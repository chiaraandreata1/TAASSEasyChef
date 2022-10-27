package com.sha.microservicerecipemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data //for getter and setters with Lombok
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ingredient_name")
    private String ingredientName;
}
