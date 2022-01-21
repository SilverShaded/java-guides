package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.Data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConvert implements Converter<String,Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConvert(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }

}
