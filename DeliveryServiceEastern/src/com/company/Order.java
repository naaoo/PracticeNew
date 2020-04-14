package com.company;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Order {

    public static void makeOrder(int customerId) {
        Scanner stringScanner = new Scanner(System.in);
        boolean orderIsComplete = false;
        openOrder(customerId);
        while (!orderIsComplete) {
            // choose dish
            int dishId = chooseDish();
            // customize dish
            System.out.println("Would you like to customize this dish? (Y/N)");
            String customize = stringScanner.nextLine().substring(0,1);
            if (customize.equalsIgnoreCase("y")) {
                // customizeDish
                customizeDish(dishId);
            }
            // asks if another dish should be entered, if not completes while loop
            System.out.println("Would you like to add another dish? (Y/N)");
            String complete = stringScanner.nextLine().substring(0,1);
            if (complete.equalsIgnoreCase("n")) {
                orderIsComplete = true;
            }
        }
        calculateOrderDetailPrice();
        calculateOrderCosts();
        updatePastOrders(customerId);
    }

    private static void updatePastOrders(int customerId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String updatePastOrders = "UPDATE customers SET past_orders = past_orders + 1 WHERE id = " + customerId;
            stmt.executeUpdate(updatePastOrders);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void customizeDish(int dishId) {
        Scanner intScanner = new Scanner(System.in);
        int mode = 0;
        while (mode != 3) {
            String changeKind = "";
            int ingredientId = 0;
            System.out.println("Would you like to:\n1: add an ingredient\n2: subtract an ingredient\n3: finished");
            mode = intScanner.nextInt();
            switch (mode) {
                case 1 : //add
                    changeKind = "add";
                    System.out.println("Please enter the ingredients' number:");
                    displayIngredients();
                    ingredientId = intScanner.nextInt();
                    break;
                case 2 : //sub
                    changeKind = "sub";
                    System.out.println("Please enter the ingredients' number:");
                    displayIngredientsOfDishCustomization(dishId);
                    ingredientId = intScanner.nextInt();
                    break;
            }
            if (mode!= 3) {
                // enter change
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
                    Statement stmt = conn.createStatement();
                    String insertChange = "INSERT INTO order_changes (order_details_id, kind, ingredient_id) VALUES ((SELECT MAX(id) FROM order_details), '"
                            + changeKind + "', " + ingredientId + ");";
                    stmt.executeUpdate(insertChange);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static int chooseDish() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose your dish by entering its number:");
        displayDishes();
        int dishId = scanner.nextInt();
        // add dish to order
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String insertDish = "INSERT INTO order_details (order_id, dish_id) VALUES ((SELECT MAX(id) FROM orders), " + dishId + ");";
            stmt.executeUpdate(insertDish);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dishId;
    }

    private static void displayDishes() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String querySelectDishes = "SELECT * FROM dishes";
            ResultSet rs = stmt.executeQuery(querySelectDishes);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                System.out.print(id + ": " + name);
                displayIngredientsOfDish(id);
                System.out.println(" - " + price + "€");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void displayIngredientsOfDish(int dishId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String querySelectIngredients = "SELECT ingredients.name FROM ((dishes INNER JOIN dishes_ingredients ON dishes.name=dishes_ingredients.dish_name) " +
                    "INNER JOIN ingredients ON dishes_ingredients.ingredient_id=ingredients.id) WHERE dishes.id = " + dishId;
            ResultSet rs = stmt.executeQuery(querySelectIngredients);
            System.out.print(" (");
            while (rs.next()) {
                String name = rs.getString("name");
                if (rs.isLast()) {
                    System.out.print(name + ")");
                } else {
                    System.out.print(name + ", ");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void displayIngredientsOfDishCustomization(int dishId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String querySelectIngredients = "SELECT ingredients.id, ingredients.name FROM ((dishes INNER JOIN dishes_ingredients ON dishes.name=dishes_ingredients.dish_name) " +
                    "INNER JOIN ingredients ON dishes_ingredients.ingredient_id=ingredients.id) WHERE dishes.id = " + dishId;
            ResultSet rs = stmt.executeQuery(querySelectIngredients);
            while (rs.next()) {
                String name = rs.getString("name");
                int ingredientId = rs.getInt("id");
                System.out.println(ingredientId + ": " + name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void displayIngredients() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String querySelectIngredients = "SELECT * FROM ingredients";
            ResultSet rs = stmt.executeQuery(querySelectIngredients);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                System.out.println(id + ": " + name + " (" + price + "€)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void openOrder(int customerId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String insertOrder = "INSERT INTO orders (customer_id) VALUES ('" + customerId + "')";
            stmt.executeUpdate(insertOrder);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void calculateOrderDetailPrice() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            String queryDishPrice = "SELECT order_details.id, dishes.price FROM order_details INNER JOIN dishes ON order_details.dish_id=dishes.id ";
            String queryAddsPrice = "SELECT order_details_id, sum(price) FROM order_changes INNER JOIN ingredients ON order_changes.ingredient_id=ingredients.id WHERE kind='add' GROUP BY order_details_id";
            ResultSet rsDishPrice = stmt.executeQuery(queryDishPrice);
            // calculate complete dish price
            while (rsDishPrice.next()) {
                int id = rsDishPrice.getInt("id");
                ResultSet rsAddsPrice = stmt1.executeQuery(queryAddsPrice);
                double price = rsDishPrice.getDouble("price");
                while (rsAddsPrice.next()) {
                    if (rsAddsPrice.getInt("order_details_id") == id) {
                        double priceAdds = rsAddsPrice.getDouble("sum(price)");
                        price = Math.round((price + priceAdds) * 100.0) / 100.0;
                    }
                }
                //write price into table order_details
                try {
                    String updateOrderDetails = "UPDATE order_details SET price = " + price + " WHERE id= " + id;
                    stmt1.executeUpdate(updateOrderDetails);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private static void calculateOrderCosts() {
        //calculate costs before discount
        Connection conn = null;
        Statement stmt;
        String queryCostsPure = "SELECT order_id, sum(price) FROM order_details GROUP BY order_details.order_id ORDER BY order_id DESC LIMIT 1";
        int orderId = 0;
        double costsPure = 0.0D;
        double deliveryCosts = 0.0D;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryCostsPure);
            while (rs.next()) {
                // get costs
                orderId = rs.getInt("order_id");
                costsPure = Math.round((rs.getDouble("sum(price)")) * 100.0) / 100.0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // calculate costs after discount
        double discountRate = selectDiscountRate(orderId);
        double costsAfterDiscount = Math.round((costsPure * (1 - discountRate)) * 100.0) / 100.0;
        // select delivery costs
        String queryDeliveryCosts = "SELECT price FROM ((locations INNER JOIN customers ON locations.name = customers.location) INNER JOIN orders ON customers.id = orders.customer_id) WHERE orders.id = " + orderId;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryDeliveryCosts);
            while (rs.next()) {
                deliveryCosts = rs.getDouble("price");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // calculate costs overall
        double costsOverall = Math.round((costsAfterDiscount + deliveryCosts) * 100.0) / 100.0;
        try {
            String updateCosts = "UPDATE orders SET costs_pure = " + costsPure + ", costs_after_discount = " + costsAfterDiscount
                    + ", delivery_costs = " + deliveryCosts  + ", costs_overall = " +  costsOverall + " WHERE id = " + orderId;
            Statement stmt1 = conn.createStatement();
            stmt1.executeUpdate(updateCosts);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static double selectDiscountRate(int orderId) {
        double discountRate = 0;
        int pastOrders = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String queryPastOrders = "SELECT customers.past_orders FROM customers INNER JOIN orders ON customers.id=orders.customer_id WHERE orders.id = " + orderId;
            ResultSet rs = stmt.executeQuery(queryPastOrders);
            while (rs.next()) {
                pastOrders = rs.getInt("past_orders");
            }
            String queryPercentage = "SELECT percentage FROM discounts WHERE needed_orders <= " + pastOrders + " ORDER BY percentage DESC LIMIT 1 ";
            rs = stmt.executeQuery(queryPercentage);
            while (rs.next()) {
                double discount = rs.getDouble("percentage");
                if (discount != 0) {
                    discountRate = discount / 100;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return discountRate;
    }
}
