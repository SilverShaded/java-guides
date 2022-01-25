package com.springinaction.tacocloud.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
public class Taco {

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5,message = "Name musr be at least 5 characters long")
    private String name;

    @Size(min = 1,message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
