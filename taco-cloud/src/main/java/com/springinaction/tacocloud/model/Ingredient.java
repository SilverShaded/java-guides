package com.springinaction.tacocloud.model;

import lombok.Data;

//玉米饼的配料
@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        //包装，蛋白质，蔬菜，奶酪，酱汁
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
