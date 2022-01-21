package com.springinaction.tacocloud.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

//玉米饼的配料
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;


    public static enum Type {
        //包装，蛋白质，蔬菜，奶酪，酱汁
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
