package com.company.view;

import com.company.Main;
import com.company.database.model.Dish;
import com.company.database.model.Ingredient;
import com.company.database.model.Location;
import com.company.database.repositories.DatabaseConnector;

import java.sql.*;

public class View {

    public static void displayIngredients() {
        for (Ingredient ingredient : Main.controller.ingredientRepository.ingredientsArr) {
            System.out.println(ingredient.id + ": " + ingredient.name + " (" + ingredient.price + "€)");
        }
        System.out.println();
    }

    public static void displayDishes() {
        for (Dish dish : Main.controller.dishRepository.dishesArr) {
            System.out.println(dish.id + ": " + dish.name + " (" + dish.price + "€)");
        }
        System.out.println();
    }

    public static void displayLocations() {
        for (Location location : Main.controller.locationRepository.locationsArr) {
            System.out.println(location.name + " (" + location.deliveryCosts + "€)");
        }
        System.out.println();
    }
}
