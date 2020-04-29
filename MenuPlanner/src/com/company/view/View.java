package com.company.view;

import com.company.Main;
import com.company.database.model.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

    public static void displayTables() {
        for (Table table : Main.controller.tableRepository.tablesArr) {
            System.out.println(table.id + ": " + table.maxSeats + " seats");
        }
        System.out.println();
    }

    public static void displayReservations() {
        DateTimeFormatter df = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");
        DateTimeFormatter dfEnd = DateTimeFormat.forPattern("HH:mm");
        for (Reservation res : Main.controller.reservationRepository.reservationArr) {
            System.out.println(res.id + ". table: " + res.tableId + ", seats: " + res.seats +
                    ", customer id: " + res.customerId +
                    ", Time: " + df.print(res.timeStart) + " - " + dfEnd.print(res.timeEnd));
        }
        System.out.println();
    }
}
