package com.company;

import java.sql.*;
import java.util.Scanner;

public class Customer {

    // asks for name, checks if already in database and asks password
    // if not in database completes signUp
    public static int logIn() {
        int userId = 0;
        Scanner stringScanner = new Scanner(System.in);
        // get data
        System.out.println("Please enter the following:\nFirst name:");
        String firstName = stringScanner.nextLine();
        System.out.println("Last name:");
        String lastName = stringScanner.nextLine();
        if (checkSignedUp(firstName, lastName)) {
            System.out.println("Welcome back " + firstName + "!");
            // complete logIn
            int tries = 3;
            while (tries != 0) {
                System.out.println("Password:");
                String password = stringScanner.nextLine();
                if (checkPassword(firstName, lastName, password)) {
                    userId = selectId(firstName, lastName);
                    break;
                } else {
                    tries--;
                    if (tries == 0) {
                        System.out.println("Log in failed!");
                        // Todo: maybe implement "user is blocked" (would need column 'blocked' [boolean] in database and checkIfBlocked after asking for name)
                    } else {
                        System.out.println("Wrong password. " + tries + " tries left");
                    }
                }
            }
        } else {
            System.out.println("Welcome to our delivery service " + firstName + "! Please continue to sign up:");
            // signUp
            completeSignUp(firstName, lastName);
            userId = selectId(firstName, lastName);
        }
        return userId;
    }

    private static void completeSignUp(String firstName, String lastName) {
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Location (just town, no address needed):");
        String location = stringScanner.nextLine();
        String password = "";
        boolean isMatching = false;
        while (!isMatching) {
            System.out.println("Password:");
            password = stringScanner.nextLine();
            System.out.println("Please enter the same password again:");
            String passwordControl = stringScanner.nextLine();
            if (password.equals(passwordControl)) {
                isMatching = true;
            } else {
                System.out.println("Passwords didn't match. Please try again:");
            }
        }
        // create customer in database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String insertUser = "INSERT INTO customers (last_name, first_name, location, password) VALUES ('" + lastName + "', '" + firstName + "', '" + location + "', '" + password + "');";
            stmt.executeUpdate(insertUser);
            //return user id
            String selectUserId = "SELECT id FROM customers ORDER BY id DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(selectUserId);
            System.out.println("Successfully signed up " + firstName + " " + lastName + "!\n");
            //ID not needed for login... so unnecessarily selected and displayed...
        } catch (SQLIntegrityConstraintViolationException exIntegrity) {
            //is thrown if location doesn't exist in database
            System.out.println("Sorry, but we don't deliver to your location.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean checkSignedUp(String firstName, String lastName) {
        boolean isSignedUp = false;
        ResultSet rs;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String selectCustomers = "SELECT * FROM customers";
            rs = stmt.executeQuery(selectCustomers);
            while (rs.next()) {
                if (rs.getString("last_name").equals(lastName) && rs.getString("first_name").equals(firstName)) {
                    isSignedUp = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isSignedUp;
    }

    private static boolean checkPassword(String firstName, String lastName, String password) {
        boolean passwordIsCorrect = false;
        ResultSet rs;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String selectCustomers = "SELECT * FROM customers";
            rs = stmt.executeQuery(selectCustomers);
            while (rs.next()) {
                if (rs.getString("last_name").equals(lastName) && rs.getString("first_name").equals(firstName) && rs.getString("password").equals(password)) {
                    passwordIsCorrect = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return passwordIsCorrect;
    }

    private static int selectId(String firstName, String lastName) {
        int userId = 0;
        ResultSet rs;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String selectCustomers = "SELECT * FROM customers";
            rs = stmt.executeQuery(selectCustomers);
            while (rs.next()) {
                if (rs.getString("last_name").equals(lastName) && rs.getString("first_name").equals(firstName)) {
                    userId = rs.getInt("id");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userId;
    }


    public static String selectLocation(int userId) {
        String location = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String selectCustomers = "SELECT location FROM customers WHERE id=" + userId;
             ResultSet rs = stmt.executeQuery(selectCustomers);
            while (rs.next()) {
                    location = rs.getString("location");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return location;
    }
}
