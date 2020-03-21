package com.company;

public class Main {

    public static void main(String[] args) {
        DeliveryService console = new DeliveryService("Console");

        Ingredient mozarella = new Ingredient(IngredientName.Mozzarella, 1.2);
        Ingredient gorgonzola = new Ingredient(IngredientName.Gorgonzola, 1.8);
        Ingredient sauce = new Ingredient(IngredientName.Sauce, 1);
        Ingredient dough = new Ingredient(IngredientName.Dough, 2.6);
        Ingredient onions = new Ingredient(IngredientName.Onions, 1.2);
        Ingredient garlic = new Ingredient(IngredientName.Garlic, 0.8);
        Ingredient salami = new Ingredient(IngredientName.Salami, 1.8);
        Ingredient pepperoni = new Ingredient(IngredientName.Pepperoni, 1.2);
        Ingredient ham = new Ingredient(IngredientName.Ham, 1.6);
        Ingredient broccoli = new Ingredient(IngredientName.Broccoli, 1.2);
        Ingredient pineapple = new Ingredient(IngredientName.Pineapple, 1.6);

        Ingredient[] formaggiIng = {dough, sauce, mozarella, gorgonzola, garlic};
        Ingredient[] salamiIng = {dough, sauce, mozarella, salami};
        Ingredient[] vegetableIng = {dough, sauce, mozarella, broccoli, onions};
        Ingredient[] meatIng = {dough, sauce, mozarella, salami, ham, pepperoni};

        Dish pFormaggi = new Dish("Pizza Formaggi", 9.8, formaggiIng, console);
        Dish pSalami = new Dish("Pizza Salami", 10.4, salamiIng, console);
        Dish pVegetable = new Dish("Pizza Vegetable", 9.6, vegetableIng, console);
        Dish pMeat = new Dish("Pizza Meat", 10.8, meatIng, console);

        console.getOrder();




    }
}
