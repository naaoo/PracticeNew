package com.company;

public class Dish {
    String name;
    double price;
    Ingredient[] ingredients;
    int amountIngredients;

    public Dish(String name, double price, Ingredient[] ingredients, DeliveryService deliveryService) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        amountIngredients = ingredients.length;
        deliveryService.addDish(this);
    }

    @Override
    public String toString() {
        return name + "  (" + price + "€)";
    }
}
