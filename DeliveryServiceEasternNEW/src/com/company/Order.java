package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Order{
    ArrayList<Dish> dishes = new ArrayList<>();
    double costsPure;
    double costsAfterDiscount;
    // not necessarily needed here since location already knows deliveryCosts:
    double deliveryCosts;
    double costsOverall;

    public Order() {
        // choose dish
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        boolean orderIsComplete = false;
        while (!orderIsComplete) {
            System.out.println("What would you like to order? (Please enter number below)");
            Dish.displayOriginalDishes();
            int dishChoice = intScanner.nextInt() - 1;
            // clones original Dish to order
            try {
            this.dishes.add(Main.allOriginalDishesArr.get(dishChoice).clone());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Would you like to customize that dish? (Y/N)");
            String customize = stringScanner.next();
            if (customize.equalsIgnoreCase("y")) {
                this.dishes.get(this.dishes.size() - 1).customizeDish();
            }
            System.out.println("Would you like to order another dish? (Y/N)");
            String nextDish = stringScanner.next();
            if (nextDish.equalsIgnoreCase("n")) {
                orderIsComplete = true;
            }
        }
        System.out.println("Order complete!");
        this.calculatePrice();
    }

    private void calculatePrice() {
        double costsP = 0.0D;
        for (Dish dish : this.dishes) {
            costsP = costsP + dish.price;
        }
        this.costsPure = Math.round(costsP * 100.0) / 100.0;
        this.costsAfterDiscount = Math.round((costsP * (1.0 - Main.customer.discountRate)) * 100.0) / 100.0;
        this.deliveryCosts = Main.customer.location.deliveryCosts;
        this.costsOverall = costsAfterDiscount + deliveryCosts;
    }

    public void updateDatabase() {
        try {
            Connection conn = DriverManager.getConnection(Main.databaseUrl + "&allowMultiQueries=true");
            Statement stmt = conn.createStatement();
            // order
            String insertOrder = "INSERT into orders (customer_id, costs_pure, costs_after_discount, delivery_costs, costs_overall)" +
                    "VALUES (" + Main.customer.id + ", " + this.costsPure + ", " + this.costsAfterDiscount + ", "
                    + this.deliveryCosts + ", " + this.costsOverall + "); ";
            // customer pastOrders
            String updatePastOrders = "UPDATE customers SET past_orders = past_orders + 1 WHERE customers.id = " + Main.customer.id + "; ";
            stmt.executeUpdate(insertOrder + updatePastOrders);
            // order details (for each dish)
            for (Dish dish : this.dishes) {
                String insertOrderDetails = "INSERT INTO order_details (order_id, dish_id, price) " +
                        "VALUES ((SELECT MAX(id) FROM orders), " + dish.id + ", " + dish.price + "); ";
                stmt.executeUpdate(insertOrderDetails);
                // order changes (for each adds + subs)
                for (Ingredient ingredient : dish.additionsArr) {
                    String insertOrderChangesAdd = "INSERT INTO order_changes (order_details_id, kind, ingredient_id) " +
                            "VALUES ((SELECT MAX(id) FROM order_details), 'add', " + ingredient.id + "); ";
                    stmt.executeUpdate(insertOrderChangesAdd);
                }
                for (Ingredient ingredient : dish.subtractionsArr) {
                    String insertOrderChangesSub = "INSERT INTO order_changes (order_details_id, kind, ingredient_id) " +
                            "VALUES ((SELECT MAX(id) FROM order_details), 'sub', " + ingredient.id + "); ";
                    stmt.executeUpdate(insertOrderChangesSub);
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void printReceipt() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("\nYour receipt:");
        int i = 1;
        for (Dish dish : this.dishes) {
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
            System.out.print(")");
            j = 0;
            for (Ingredient ingredient : dish.additionsArr) {
                if (j == 0) {
                    System.out.print(" (+ " + ingredient.name);
                } else {
                    System.out.print(", " + ingredient.name);
                }
                j++;
            }
            System.out.println(") -- " + df.format(dish.price) + "€");
        }
        System.out.println("-------------------------------");

        System.out.println("Sum:                     " + df.format(this.costsPure) + "€");
        if (Main.customer.discountRate != 0.0) {
            System.out.println("After discount:          " + df.format(this.costsAfterDiscount) + "€");
        }
        System.out.println("+ delivery costs:        " + df.format(this.deliveryCosts) + "€");
        System.out.println("                         ------");
        System.out.println("Final price:             " + df.format(this.costsOverall) + "€\n\nThank you for your order!");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
