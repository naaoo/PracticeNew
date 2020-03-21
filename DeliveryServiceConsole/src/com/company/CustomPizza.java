package com.company;

import java.util.Arrays;

public class CustomPizza {
    String name;
    double price;
    Ingredient[] customIngredients = new Ingredient[20];

    public CustomPizza(String name, double price, Ingredient[] customIngredients) {
        this.name = name;
        this.price = price;
        this.customIngredients = customIngredients;
    }

    @Override
    public String toString() {
        return name + ", Price: " + price + "€";
    }
}
