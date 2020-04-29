package com.company.view;

import com.company.Main;
import com.company.database.model.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class View {

    // displays all known ingredients and their prices
    public static void displayAllIngredients() {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Ingredient ingredient : Main.controller.ingredientRepository.ingredientArr) {
            System.out.println(i + ": " + ingredient.name + " (" + df.format(ingredient.price) + "€)");
            i++;
        }
    }

    // displays all dishes in their original form (not customized)
    public static void displayOriginalDishes() {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Dish dish : Main.controller.dishRepository.dishArr) {
            System.out.println(i + ": " + dish.name + " (" + df.format(dish.price) + "€)");
            i++;
        }
    }

    // displays ingredients of a certain dish
    public static void displayIngredients(Dish dish) {
        int i = 1;
        for (Ingredient ingredient : dish.ingredientsArr) {
            System.out.println(i + ": " + ingredient.name);
            i++;
        }
    }

    // prints receipt of a certain order
    public static void printReceipt(Order order) {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("\nYour receipt:");
        int i = 1;
        for (Dish dish : order.dishes) {
            System.out.print(i + ": " + dish.name);
            i++;
            int j = 0;
            for (Ingredient ingredient : dish.subtractionsArr) {
                if (j == 0) {
                    System.out.print(" (- " + ingredient.name);
                } else {
                    System.out.print(", " + ingredient.name);
                }
                j++;
            }
            if (dish.subtractionsArr.size() != 0) {
                System.out.print(")");
            }
            j = 0;
            for (Ingredient ingredient : dish.additionsArr) {
                if (j == 0) {
                    System.out.print(" (+ " + ingredient.name);
                } else {
                    System.out.print(", " + ingredient.name);
                }
                j++;
            }
            if (dish.additionsArr.size() != 0) {
                System.out.println(")");
            }
            System.out.println(" -- " + df.format(dish.price) + "€");
        }
        System.out.println("-------------------------------");
        System.out.println("Sum:                     " + df.format(order.costsPure) + "€");
        if (Main.controller.customer.discountRate != 0.0) {
            System.out.println("After discount:          " + df.format(order.costsAfterDiscount) + "€");
        }
        System.out.println("+ delivery costs:        " + df.format(order.deliveryCosts) + "€");
        System.out.println("                         ------");
        System.out.println("Final price:             " + df.format(order.costsOverall) + "€\n\nThank you for your order!");
    }

    public static void displayTables(ArrayList<Table> tables) {
        for (Table table : tables) {
            System.out.println("Table " + table.id + " (max seats: " + table.maxSeats + ")");
        }
    }

    public static void displayMyReservations() {
        DateTimeFormatter df = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
        for (Reservation reservation : Main.controller.reservationRepository.reservationArr) {
            if (reservation.customerId == Main.controller.customer.id) {
                System.out.println("ID: " + reservation.id + ", time: " + df.print(reservation.timeStart));
            }
        }
    }
}
