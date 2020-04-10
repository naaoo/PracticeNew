package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ownerMakeEntry();
    }

    private static void ownerMakeEntry() {
        Scanner intScanner = new Scanner(System.in);
        int mode = 0;
        System.out.println("Hello restaurant owner! What would you like to do?");
        while (mode != 4) {
            System.out.println("1: add a new dish (all used ingredients have to be entered before)\n2: add a new ingredient\n3: add a new delivery location\n4: quit program");
            mode = intScanner.nextInt();
            switch (mode) {
                case 1: addDish(); break;
                case 2: addIngredient(); break;
                case 3: addLocation(); break;
            }
        }
        System.out.println("Until next time...");
    }

    private static void addDish() {
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
            exIntegrity.printStackTrace();
            //System.out.println("Dish already exists");
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

    private static double getCalculatedPrice(String dishName) {
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

    private static void addIngredientToDish(String dishName) {
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
        System.out.println();
    }

    private static void addIngredient() {
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

    private static void addLocation() {
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
