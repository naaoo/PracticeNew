package com.company;

import java.util.ArrayList;

public class Main {

    // Todo: (Maybe) if an error occurs in one step (e.g. Customer.login) program still tries to run all other steps. would be a problem in reality

    static String databaseUrl = "jdbc:mysql://localhost:3306/restaurant?user=root";
    static Customer customer;
    static ArrayList<Ingredient> allIngredientsArr = new ArrayList<>();
    static ArrayList<Dish> allOriginalDishesArr = new ArrayList<>();
    static Order order;

    public static void main(String[] args) {

        // logIn or signIn (if customer is not known), then retrieves customer and location data, saves into objects
        Customer.logIn();
        // retrieves all ingredients and saves to <allIngredientArr>
        Ingredient.retrieveAll();
        // retrieves all dishes, saves to <allOriginalDishesArr> and fills ingredientsArray of each dish
        Dish.retrieveDishes();
        // whole order process
        order = new Order();
        order.updateDatabase();
        order.printReceipt();
    }
}
