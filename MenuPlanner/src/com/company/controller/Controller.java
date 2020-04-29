package com.company.controller;

import com.company.Main;
import com.company.database.model.Dish;
import com.company.database.model.Ingredient;
import com.company.database.model.Location;
import com.company.database.model.Table;
import com.company.database.repositories.*;
import com.company.view.View;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public static IngredientRepository ingredientRepository = new IngredientRepository();
    public static DishRepository dishRepository = new DishRepository();
    public static LocationRepository locationRepository = new LocationRepository();
    public static TableRepository tableRepository = new TableRepository();
    public static ReservationRepository reservationRepository = new ReservationRepository();

    public static void ownerProgram() {
        Scanner intScanner = new Scanner(System.in);
        int mode = 0;
        System.out.println("Hello restaurant owner! What would you like to manage?");
        while (mode != 8) {
            System.out.println("1: dishes\n2: ingredients\n3: tables\n4: reservations\n" +
                    "5: delivery locations\n6: make queries\n7: exports\n8: quit");
            mode = intScanner.nextInt();
            int subMode = 0;
            switch (mode) {
                case 1:
                    while (subMode != 3) {
                        System.out.println("1: display dishes\n2: add dishes\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: View.displayDishes(); break;
                            case 2: addDish(); break;
                        }
                    } break;
                case 2:
                    while (subMode != 3) {
                        System.out.println("1: display ingredients\n2: add ingredient\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: View.displayIngredients(); break;
                            case 2: addIngredient(); break;
                        }
                    } break;
                case 3:
                    while (subMode != 3) {
                        System.out.println("1: display tables\n2: add table\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: View.displayTables(); break;
                            case 2: addTable(); break;
                        }
                    } break;
                case 4:
                    while (subMode != 3) {
                        System.out.println("1: display reservations\n2: cancel reservation\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: View.displayReservations(); break;
                            case 2: cancelReservation(); break;
                        }
                    } break;
                case 5:
                    while (subMode != 3) {
                        System.out.println("1: display locations\n2: add location\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1:
                                View.displayLocations();
                                break;
                            case 2:
                                addLocation();
                                break;
                        }
                    } break;
                case 6:
                    Explorer.ownerMakeQueries();
                    break;
                case 7:
                    while (subMode != 3) {
                        System.out.println("1: export orders\n2: export ingredient consumption\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: Exporter.exportOrders(); break;
                            case 2: Exporter.exportIngredientConsumption(); break;
                        }
                    }
            }
        }
        System.out.println("Have a nice day\n");
    }

    public static void addIngredient() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        System.out.println("What's the name of the ingredient?");
        String name = stringScanner.nextLine();
        System.out.println("Ingredient costs? (Use ',' if necessary)");
        double price = doubleScanner.nextDouble();
        Ingredient ingredient = new Ingredient(null, name, price);
        ingredientRepository.create(ingredient);
        ingredientRepository.findAll();
        System.out.println(name + " has been added to ingredients");
    }

    public static void addLocation() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        System.out.println("What's the name of the location?");
        String name = stringScanner.nextLine();
        System.out.println("Delivery costs? (Use ',' if necessary)");
        double costs = doubleScanner.nextDouble();
        Location location = new Location(name, costs);
        locationRepository.create(location);
        locationRepository.findAll();
    }

    public static void addDish() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        System.out.println("What's the name of the dish?");
        String name = stringScanner.nextLine();
        boolean isNotCompleted = true;
        // add ingredients to dish until "N"
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (isNotCompleted) {
            System.out.println("Do you want to add an ingredient? (Y/N)");
            String anotherIngredient = stringScanner.nextLine();
            if (anotherIngredient.equalsIgnoreCase("Y")) {
                ingredients.add(addIngredientToDish());
            } else {
                isNotCompleted = false;
            }
        }
        double calculatedPrice = getCalculatedPrice(ingredients);
        System.out.println("Base price (only ingredients): " + calculatedPrice +
                "€\nPlease enter selling price below (Use ',' if necessary)");
        double sellingPrice = doubleScanner.nextDouble();
        Dish dish = new Dish (null, name, sellingPrice);
        dishRepository.create(dish);
        dishRepository.insertDishIngredients(ingredients, dish);
        System.out.println(name + " has been added to your menu");
    }

    public static double getCalculatedPrice(ArrayList<Ingredient> ingredients) {
        double price = 0;
        for (Ingredient ingredient : ingredients) {
            price = price + ingredient.price;
        }
        return price;
    }

    public static Ingredient addIngredientToDish() {
        Ingredient ingredient = null;
        Scanner intScanner = new Scanner(System.in);
        View.displayIngredients();
        System.out.println("Please type in the ingredients' number below:");
        int ingredientId = intScanner.nextInt();
        for (Ingredient ing : Main.controller.ingredientRepository.ingredientsArr) {
            if (ing.id == ingredientId) {
                try {
                    ingredient = ing.clone();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ingredient;
    }

    public static void addTable() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("How many seats does that table have?");
        int seats = intScanner.nextInt();
        Table table = new Table(null, seats);
        tableRepository.create(table);
        tableRepository.findAll();
    }

    public static void cancelReservation() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("Which reservation should be cancelled? (Please enter below)");
        View.displayReservations();
        int reservationId = intScanner.nextInt();
        reservationRepository.cancel(reservationId);
    }

}
