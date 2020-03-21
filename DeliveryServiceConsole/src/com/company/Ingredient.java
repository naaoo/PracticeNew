package com.company;

public class Ingredient {
    IngredientName name;
    String ingridientString;
    double price;
    public static int amountIngredients = 0;
    public static Ingredient[] allIngredients = new Ingredient[20];

    public Ingredient(IngredientName name, double price) {
        this.name = name;
        this.price = price;
        this.ingridientString = toString();
        allIngredients[this.amountIngredients] = this;
        amountIngredients++;

    }

    @Override
    public String toString() {
        return "" + name;
    }
}
