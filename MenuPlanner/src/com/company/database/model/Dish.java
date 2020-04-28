package com.company.database.model;

import java.util.ArrayList;

public class Dish {
    public Integer id;
    public String name;
    public double price;
    public ArrayList<Ingredient> ingredientsArr = new ArrayList<>();

    public Dish(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
