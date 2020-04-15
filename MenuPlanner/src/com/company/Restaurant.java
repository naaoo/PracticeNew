package com.company;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Restaurant {

    // future function ideas:
    // delete/change existing dishes/ingredients/locations
    // make better use of objects (Dish, Ingredient, Location)
    //Database url should have been set as parameter once and retrieved from there instead of setting it at every connection...

    // Program 1: from owners side
    public static void ownerProgram() {
        Scanner intScanner = new Scanner(System.in);
        int mode = 0;
        System.out.println("Hello restaurant owner! What would you like to manage?");
        while (mode != 6) {
            System.out.println("1: dishes\n2: ingredients\n3: delivery locations\n4: make queries\n5: exports\n6: quit");
            mode = intScanner.nextInt();
            int subMode = 0;
            switch (mode) {
                case 1:
                    while (subMode != 3) {
                        System.out.println("1: display dishes\n2: add dishes\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: displayDishes(); break;
                            case 2: addDish(); break;
                        }
                    } break;
                case 2:
                    while (subMode != 3) {
                        System.out.println("1: display ingredients\n2: add ingredient\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: displayIngredients(); break;
                            case 2: addIngredient(); break;
                        }
                    } break;
                case 3:
                    while (subMode != 3) {
                        System.out.println("1: display locations\n2: add location\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: displayLocations(); break;
                            case 2: addLocation(); break;
                        }
                    } break;
                case 4:
                    ownerMakeQueries();
                    break;
                case 5:
                    while (subMode != 3) {
                        System.out.println("1: export orders\n2: export ingredient consumption\n3: back to menu");
                        subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1: exportOrders(); break;
                            case 2: exportIngredientConsumption(); break;
                        }
                    }
            }
        }
        System.out.println("Have a nice day\n");
    }

    private static void exportOrders() {
        ResultSet rs;
        File orders = new File("C:\\Users\\David\\Desktop\\Orders.csv");
        try {
            // only creates new file if not already existing, false for overwrite
            orders.createNewFile();
            FileWriter writer = new FileWriter(orders, false);
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
                Statement stmt = conn.createStatement();
                String query = "SELECT orders.id AS orderId, customers.id AS customerId, orders.costs_overall " +
                        "FROM orders INNER JOIN customers ON orders.customer_id = customers.id ";
                rs = stmt.executeQuery(query);
                writer.write("OrderId;CustomerId;PriceOverall");
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    int customerId = rs.getInt("customerId");
                    double costs = Math.round(rs.getDouble("costs_overall") * 100.0) / 100.0;
                    writer.write("\n" + orderId + ";" + customerId + ";" + costs);
                }
                System.out.println("Export completed");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void exportIngredientConsumption() {
        File consumption = new File("C:\\Users\\David\\Desktop\\Consumption.csv");
        try {
            // only creates new file if not already existing, false for overwrite
            consumption.createNewFile();
            FileWriter writer = new FileWriter(consumption, false);
            writer.write("IngredientId;IngredientName;Amount");
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
                Statement stmt1 = conn.createStatement();
                String detailCount = "SELECT ingredients.id, ingredients.name, COUNT(ingredients.id) AS count " +
                        "FROM (((order_details INNER JOIN dishes ON order_details.dish_id = dishes.id) " +
                        "INNER JOIN dishes_ingredients ON dishes.name = dishes_ingredients.dish_name) " +
                        "INNER JOIN ingredients ON dishes_ingredients.ingredient_id = ingredients.id) " +
                        "GROUP BY ingredients.id";
                ResultSet rsDetailCount = stmt1.executeQuery(detailCount);
                Statement stmt2 = conn.createStatement();
                String changes = "SELECT kind, ingredients.id, ingredients.name, COUNT(ingredient_id) " +
                        "FROM order_changes INNER JOIN ingredients ON order_changes.ingredient_id = ingredients.id " +
                        "GROUP BY ingredients.id, kind";
                ResultSet rsChanges = stmt2.executeQuery(changes);
                while (rsDetailCount.next()) {
                    int ingredientId = rsDetailCount.getInt("id");
                    String name = rsDetailCount.getString("name");
                    int count = rsDetailCount.getInt("count");
                    rsChanges.beforeFirst();
                    // subtract/add changes to ingredient count
                    while (rsChanges.next()) {
                        String kind = rsChanges.getString("kind");
                        int ingId = rsChanges.getInt("id");
                        int amount = rsChanges.getInt("COUNT(ingredient_id)");
                        if (ingredientId == ingId) {
                            if (kind.equals("add")) {
                                count = count + amount;
                            } else if (kind.equals("sub")) {
                                count = count - amount;
                            }
                        }
                    }
                    writer.write("\n" + ingredientId + ";" + name + ";" + count);
                }
                System.out.println("Export completed");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void ownerMakeQueries() {
        Scanner intScanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.00");
        ResultSet rs;
        int mainMode = 0;
        while (mainMode != 7) {
            try {
                String query = "";
                System.out.println("What would you like to know?\n1: amount orders overall\n2: orders per customer\n3: orders per location\n4: revenue\n5: most ordered dish\n6: orders per dish\n7: back");
                mainMode = intScanner.nextInt();
                switch (mainMode) {
                    case 1:
                        query = "SELECT COUNT(*) FROM orders";
                        rs = makeQuery(query);
                        while (rs.next()) {
                            System.out.println("Amount of orders overall: " + rs.getInt("COUNT(*)"));
                        }
                        break;
                    case 2:
                        query = "SELECT customers.first_name, customers.last_name, COUNT(*) " +
                                "FROM orders INNER JOIN customers ON orders.customer_id = customers.id GROUP BY customer_id";
                        rs = makeQuery(query);
                        System.out.println("Amount of orders per customer:");
                        while (rs.next()) {
                            String name = rs.getString("first_name") + " " + rs.getString("last_name");
                            int orders = rs.getInt("COUNT(*)");
                            System.out.println(name + "\t\t" + orders);
                        }
                        break;
                    case 3:
                        query = "SELECT locations.name, COUNT(*) " +
                                "FROM ((orders INNER JOIN customers ON orders.customer_id = customers.id) INNER JOIN locations ON customers.location = locations.name) " +
                                "GROUP BY locations.name ";
                        rs = makeQuery(query);
                        System.out.println("Amount of orders per location:");
                        while (rs.next()) {
                            String location = rs.getString("name");
                            int orders = rs.getInt("COUNT(*)");
                            System.out.println(location + ":\t\t" + orders);
                        }
                        break;
                    case 4:
                        System.out.println("What is your revenue criteria?\n1: Overall\n2: per customer\n3: per location");
                        int subMode = intScanner.nextInt();
                        switch (subMode) {
                            case 1:
                                query = "SELECT sum(costs_overall) FROM orders";
                                rs = makeQuery(query);
                                while (rs.next()) {
                                    double revenue = Math.round(rs.getDouble("sum(costs_overall)") * 100.0) / 100.0;
                                    System.out.println("Revenue overall: " + df.format(revenue) + "€");
                                }
                                break;
                            case 2:
                                query = "SELECT customers.id, customers.first_name, customers.last_name, sum(costs_overall) " +
                                        "FROM orders INNER JOIN customers ON orders.customer_id = customers.id GROUP BY customers.id";
                                rs = makeQuery(query);
                                System.out.println("Revenue per customer:");
                                while (rs.next()) {
                                    String name = rs.getString("first_name") + " " + rs.getString("last_name");
                                    double revenue = Math.round(rs.getDouble("sum(costs_overall)") * 100.0) / 100.0;
                                    System.out.println(name + ":\t" + df.format(revenue) + "€");
                                }
                                break;
                            case 3:
                                query = "SELECT locations.name, sum(costs_overall) " +
                                        "FROM ((orders INNER JOIN customers ON orders.customer_id = customers.id) INNER JOIN locations ON customers.location = locations.name)" +
                                        "GROUP BY locations.name";
                                rs = makeQuery(query);
                                System.out.println("Revenue per loccation:");
                                while (rs.next()) {
                                    String name = rs.getString("name");
                                    double revenue = Math.round(rs.getDouble("sum(costs_overall)") * 100.0) / 100.0;
                                    System.out.println(name + ":\t" + df.format(revenue) + "€");
                                }
                                break;
                        }
                        break;
                    case 5:
                        query = "SELECT dishes.name, count(order_details.id) " +
                                "FROM order_details INNER JOIN dishes ON dishes.id = order_details.dish_id GROUP BY dish_id ORDER BY count(order_details.id) DESC LIMIT 1 ";
                        rs = makeQuery(query);
                        while (rs.next()) {
                            String name = rs.getString("name");
                            int count = rs.getInt("count(order_details.id)");
                            System.out.println("Most ordered dish:\n" + name + " (Count: " + count + ")");
                        }
                        break;
                    case 6:
                        query = "SELECT dishes.name, count(order_details.id) " +
                                // not sure if i understood assignment correctly: what is the criteria (X) for how often (X) was ordered? here: X = dishes
                                "FROM order_details INNER JOIN dishes ON dishes.id = order_details.dish_id GROUP BY dish_id ORDER BY count(order_details.id) DESC";
                        rs = makeQuery(query);
                        System.out.println("Orders per dish:");
                        while (rs.next()) {
                            String name = rs.getString("name");
                            int count = rs.getInt("count(order_details.id)");
                            System.out.println(count + ": " + name);
                        }
                        break;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println();
        }
    }

    private static ResultSet makeQuery(String query) {
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static void addDish() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        System.out.println("What's the name of the dish?");
        String name = stringScanner.nextLine();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String insertDish = "INSERT INTO dishes (name) VALUES ('" + name + "');";
            stmt.executeUpdate(insertDish);
        } catch (SQLIntegrityConstraintViolationException exIntegrity) {
            System.out.println("Dish already exists");
            return;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
        boolean isNotCompleted = true;
        // add ingredients to dish until "N"
        while (isNotCompleted) {
            System.out.println("Do you want to add an ingredient? (Y/N)");
            String anotherIngredient = stringScanner.nextLine();
            if (anotherIngredient.equalsIgnoreCase("Y")) {
                addIngredientToDish(name);
            } else {
                isNotCompleted = false;
            }
        }
        // display ingredientPrice
        double calculatedPrice = getCalculatedPrice(name);
        System.out.println("Base price (only ingredients): " + calculatedPrice + "€\nPlease enter selling price below (Use ',' if necessary)");
        double sellingPrice = doubleScanner.nextDouble();
        // update sellingPrice
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String updatePrice = "UPDATE dishes SET price = " + sellingPrice + "WHERE name = '" + name + "';";
            stmt.executeUpdate(updatePrice);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
        System.out.println(name + " has been added to your menu");
    }

    public static double getCalculatedPrice(String dishName) {
        double price = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String queryCalculatedPrice = "SELECT sum(price) FROM ingredients INNER JOIN dishes_ingredients ON ingredients.id = dishes_ingredients.ingredient_id WHERE dish_name = '" + dishName + "';";
            ResultSet rs = stmt.executeQuery(queryCalculatedPrice);
            while (rs.next()) {
                price = rs.getDouble("sum(price)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    public static void addIngredientToDish(String dishName) {
        Scanner intScanner = new Scanner(System.in);
        displayIngredients();
        System.out.println("Please type in the ingredients' number below:");
        int ingredient = intScanner.nextInt();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String insertIngredient = "INSERT into dishes_ingredients (dish_name, ingredient_id) VALUES ('" + dishName + "', " + ingredient + ");";
            stmt.executeUpdate(insertIngredient);
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }

    }

    public static void displayIngredients() {
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
        System.out.println();
    }

    public static void displayDishes() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String querySelectDishes = "SELECT * FROM dishes";
            ResultSet rs = stmt.executeQuery(querySelectDishes);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                System.out.println(id + ": " + name + " (" + price + "€)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println();
    }

    public static void addIngredient() {
        Connection conn;
        Scanner stringScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        System.out.println("What's the name of the ingredient?");
        String name = stringScanner.nextLine();
        System.out.println("Ingredient costs? (Use ',' if necessary)");
        double costs = doubleScanner.nextDouble();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String insertIngredient = "INSERT INTO ingredients (name, price) VALUES ('" + name + "', " + costs + ");";
            stmt.executeUpdate(insertIngredient);
        } catch (SQLIntegrityConstraintViolationException exDoub) {
            System.out.println("Ingredient already exists");
            return;
            //Todo: (Maybe) Could ask if price should be changed if this is thrown
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
        System.out.println(name + " has been added to ingredients");
    }

    public static void displayLocations() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String querySelectLocations = "SELECT * FROM locations";
            ResultSet rs = stmt.executeQuery(querySelectLocations);
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                System.out.println(name + " (" + price + "€)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println();
    }

    public static void addLocation() {
        Connection conn;
        Scanner stringScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        System.out.println("What's the name of the location?");
        String name = stringScanner.nextLine();
        System.out.println("Delivery costs? (Use ',' if necessary)");
        double costs = doubleScanner.nextDouble();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String insertLocation = "INSERT INTO locations (name, price) VALUES ('" + name + "', " + costs + ");";
            stmt.executeUpdate(insertLocation);
        } catch (SQLIntegrityConstraintViolationException exDoub) {
            System.out.println("Location already exists");
            //Todo: (Maybe) Could ask if price should be changed if this is thrown
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
