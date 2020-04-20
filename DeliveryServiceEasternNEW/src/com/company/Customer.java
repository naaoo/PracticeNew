package com.company;

import java.sql.*;
import java.util.Scanner;

public class Customer {
    int id;
    String firstName;
    String lastName;
    Location location;
    String password;
    int amountPastOrders;
    double discountRate;

    // All following methods: needed for signing in, retrieving customer data and creating instance Main.customer
    public static void logIn() {
        Scanner stringScanner = new Scanner(System.in);
        // get data
        System.out.println("Welcome to our delivery service!\nPlease enter the following:\nFirst name:");
        String firstName = stringScanner.next();
        System.out.println("Last name:");
        String lastName = stringScanner.next();
        if (checkSignedUp(firstName, lastName)) {
            System.out.println("Welcome back " + firstName + "!");
            // get Data
            retrieveCustomerData(firstName, lastName);
            //complete login
            int tries = 3;
            while (tries != 0) {
                System.out.println("Password:");
                String password = stringScanner.next();
                if (password.equals(Main.customer.password)) {
                    System.out.println("Password correct");
                    break;
                } else {
                    tries--;
                    if (tries == 0) {
                        System.out.println("Log in failed!");
                        // Todo: maybe implement "user is blocked" (would need column 'blocked' [boolean] in database and checkIfBlocked after asking for name)
                    } else {
                        if (tries == 1) {
                            System.out.println("Wrong password. " + tries + " try left");
                        } else {
                            System.out.println("Wrong password. " + tries + " tries left");
                        }
                    }
                }
            }
        } else {
            // signUp new customer
            System.out.println("Welcome to our delivery service " + firstName + "! Please continue to sign up:");
            signUp(firstName, lastName);
            retrieveCustomerData(firstName, lastName);
        }
    }

    private static boolean checkSignedUp(String firstName, String lastName) {
        boolean isSignedUp = false;
        ResultSet rs;
        try {
            Connection conn = DriverManager.getConnection(Main.databaseUrl);
            Statement stmt = conn.createStatement();
            String selectCustomers = "SELECT * FROM customers";
            rs = stmt.executeQuery(selectCustomers);
            while (rs.next()) {
                if (rs.getString("last_name").equals(lastName) && rs.getString("first_name").equals(firstName)) {
                    isSignedUp = true;
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isSignedUp;
    }

    // retrieves customer data and generates customer + location
    private static void retrieveCustomerData(String firstName, String lastName) {
        int id = 0;
        String locationName = "";
        double locationPrice = 0.0D;
        String password = "";
        int amountPastOrders = 0;
        double discountRate = 0.0D;
        try {
            Connection conn = DriverManager.getConnection(Main.databaseUrl);
            Statement stmt = conn.createStatement();
            String queryCustomerData = "SELECT * FROM " +
                    "((customers INNER JOIN discounts ON customers.past_orders > discounts.needed_orders) " +
                    "INNER JOIN locations ON customers.location = locations.name) " +
                    "WHERE customers.first_name = '" + firstName + "' AND customers.last_name = '" + lastName +
                    "' ORDER BY percentage DESC LIMIT 1 ";
            ResultSet rs = stmt.executeQuery(queryCustomerData);
            while (rs.next()) {
                id = rs.getInt("id");
                locationName = rs.getString("name");
                locationPrice = Math.round(rs.getDouble("price") * 100.0) / 100.0;
                password = rs.getString("password");
                amountPastOrders = rs.getInt("past_orders");
                discountRate = rs.getDouble("percentage") / 100.0;
            }
            stmt.close();
            conn.close();
            Main.customer = new Customer(id, firstName, lastName, locationName, locationPrice, password, amountPastOrders, discountRate);
        } catch (SQLException ex) {
            System.out.println("Customer data not successfully retrieved!");
            ex.printStackTrace();
        }
    }

    // signs new customer up and writes into database
    private static void signUp(String firstName, String lastName) {
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Location (just town, no address needed):");
        String location = stringScanner.next();
        String password = "";
        boolean isMatching = false;
        while (!isMatching) {
            System.out.println("Password:");
            password = stringScanner.next();
            System.out.println("Please enter the same password again:");
            String passwordControl = stringScanner.next();
            if (password.equals(passwordControl)) {
                isMatching = true;
            } else {
                System.out.println("Passwords didn't match. Please try again:");
            }
        }
        // write into database
        try {
            Connection conn = DriverManager.getConnection(Main.databaseUrl);
            Statement stmt = conn.createStatement();
            String insertUser = "INSERT INTO customers (last_name, first_name, location, password) " +
                    "VALUES ('" + lastName + "', '" + firstName + "', '" + location + "', '" + password + "')";
            stmt.executeUpdate(insertUser);
            stmt.close();
            conn.close();
        } catch (SQLIntegrityConstraintViolationException exIntegrity) {
            //is thrown if location doesn't exist in database
            System.out.println("Sorry, but we don't deliver to your location.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Customer(int id, String firstName, String lastName, String locationName, double locationPrice, String password, int amountPastOrders, double discountRate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = new Location(locationName, locationPrice);
        this.password = password;
        this.amountPastOrders = amountPastOrders;
        this.discountRate = discountRate;
    }
}
