package com.springinaction.tacocloud.Controller;

import com.springinaction.tacocloud.Data.IngredientRepository;
import com.springinaction.tacocloud.model.Taco;
import com.springinaction.tacocloud.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import com.springinaction.tacocloud.model.Ingredient;
import com.springinaction.tacocloud.model.Ingredient.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Type.values();
        for (Type type : types) {
            //eg:${wrap} value: [{"FLTO", "Flour Tortilla"},{"COTO", "Corn Tortilla"}]
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients,type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("taco",new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
        //valid format have errors
        if (errors.hasErrors()) {
            return "design";
        }

        log.info("Processing taco: "+ taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients,Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
