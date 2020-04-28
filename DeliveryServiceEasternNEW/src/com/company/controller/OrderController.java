package com.company.controller;

import com.company.Main;
import com.company.database.model.Dish;
import com.company.database.model.Order;
import com.company.view.View;

import java.util.Scanner;

public class OrderController {

    public static Order order() {
        // choose dish
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        Order order = new Order();
        boolean orderIsComplete = false;
        while (!orderIsComplete) {
            System.out.println("What would you like to order? (Please enter number below)");
            View.displayOriginalDishes();
            int dishChoice = intScanner.nextInt() - 1;
            // clones original Dish to order
            try {
                order.dishes.add(Main.controller.dishRepository.dishArr.get(dishChoice).clone());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Would you like to customize that dish? (Y/N)");
            String customize = stringScanner.next();
            if (customize.equalsIgnoreCase("y")) {
                // picks last added dish and customizes
                DishController.customizeDish(order.dishes.get(order.dishes.size() - 1));
            }
            System.out.println("Would you like to order another dish? (Y/N)");
            String nextDish = stringScanner.next();
            if (nextDish.equalsIgnoreCase("n")) {
                orderIsComplete = true;
            }
        }
        System.out.println("Order complete!");
        order = calculatePrice(order);
        return order;
    }

    private static Order calculatePrice(Order order) {
        double costsP = 0.0D;
        for (Dish dish : order.dishes) {
            costsP = costsP + dish.price;
        }
        order.costsPure = Math.round(costsP * 100.0) / 100.0;
        order.costsAfterDiscount = Math.round((costsP * (1.0 - Main.controller.customer.discountRate)) * 100.0) / 100.0;
        order.deliveryCosts = Main.controller.customer.location.deliveryCosts;
        order.costsOverall = order.costsAfterDiscount + order.deliveryCosts;
        return order;
    }
}
