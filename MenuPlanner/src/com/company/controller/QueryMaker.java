package com.company.controller;

import com.company.database.repositories.QueryConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class QueryMaker {
    public static void ownerMakeQueries() {
        Scanner intScanner = new Scanner(System.in);
        int mainMode = 0;
        while (mainMode != 7) {
            System.out.println("What would you like to know?\n1: amount orders overall\n2: orders per customer\n3: orders per location\n4: revenue\n5: most ordered dish\n6: orders per dish\n7: back");
            mainMode = intScanner.nextInt();
            switch (mainMode) {
                case 1:
                    queryOrdersOverall();
                    break;
                case 2:
                    queryOrdersPerCustomer();
                    break;
                case 3:
                    queryOrderPerLocation();
                    break;
                case 4:
                    System.out.println("What is your revenue criteria?\n1: Overall\n2: per customer\n3: per location");
                    int subMode = intScanner.nextInt();
                    switch (subMode) {
                        case 1:
                            queryRevenueOverall();
                            break;
                        case 2:
                            queryRevenueCustomer();
                            break;
                        case 3:
                            queryRevenueLocation();
                            break;
                    }
                    break;
                case 5:
                    queryMostOrderedDish();
                    break;
                case 6:
                    queryOrdersPerDish();
                    break;
            }
            System.out.println();
        }
    }

    private static void queryOrdersOverall() {
        try {
            String query = "SELECT COUNT(*) FROM orders";
            ResultSet rs = QueryConnection.makeQuery(query);
            while (rs.next()) {
                System.out.println("Amount of orders overall: " + rs.getInt("COUNT(*)"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void queryOrdersPerCustomer() {
        try {
            String query = "SELECT customers.first_name, customers.last_name, COUNT(*) " +
                    "FROM orders INNER JOIN customers ON orders.customer_id = customers.id GROUP BY customer_id";
            ResultSet rs = QueryConnection.makeQuery(query);
            System.out.println("Amount of orders per customer:");
            while (rs.next()) {
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                int orders = rs.getInt("COUNT(*)");
                System.out.println(name + ": " + orders);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void queryOrderPerLocation() {
        try {
            String query = "SELECT locations.name, COUNT(*) " +
                    "FROM ((orders INNER JOIN customers ON orders.customer_id = customers.id) INNER JOIN locations ON customers.location = locations.name) " +
                    "GROUP BY locations.name ";
            ResultSet rs = QueryConnection.makeQuery(query);
            System.out.println("Amount of orders per location:");
            while (rs.next()) {
                String location = rs.getString("name");
                int orders = rs.getInt("COUNT(*)");
                System.out.println(location + ": " + orders);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void queryRevenueOverall() {
        DecimalFormat df = new DecimalFormat("#.00");
        try {
            String query = "SELECT sum(costs_overall) FROM orders";
            ResultSet rs = QueryConnection.makeQuery(query);
            while (rs.next()) {
                double revenue = Math.round(rs.getDouble("sum(costs_overall)") * 100.0) / 100.0;
                System.out.println("Revenue overall: " + df.format(revenue) + "€");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void queryRevenueCustomer() {
        DecimalFormat df = new DecimalFormat("#.00");
        try {
            String query = "SELECT customers.id, customers.first_name, customers.last_name, sum(costs_overall) " +
                    "FROM orders INNER JOIN customers ON orders.customer_id = customers.id GROUP BY customers.id";
            ResultSet rs = QueryConnection.makeQuery(query);
            System.out.println("Revenue per customer:");
            while (rs.next()) {
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                double revenue = Math.round(rs.getDouble("sum(costs_overall)") * 100.0) / 100.0;
                System.out.println(name + ": " + df.format(revenue) + "€");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void queryRevenueLocation() {
        DecimalFormat df = new DecimalFormat("#.00");
        try {
            String query = "SELECT locations.name, sum(costs_overall) " +
                    "FROM ((orders INNER JOIN customers ON orders.customer_id = customers.id) INNER JOIN locations ON customers.location = locations.name)" +
                    "GROUP BY locations.name";
            ResultSet rs = QueryConnection.makeQuery(query);
            System.out.println("Revenue per location:");
            while (rs.next()) {
                String name = rs.getString("name");
                double revenue = Math.round(rs.getDouble("sum(costs_overall)") * 100.0) / 100.0;
                System.out.println(name + ": " + df.format(revenue) + "€");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void queryMostOrderedDish() {
        try {
            String query = "SELECT dishes.name, count(order_details.id) " +
                    "FROM order_details INNER JOIN dishes ON dishes.id = order_details.dish_id GROUP BY dish_id ORDER BY count(order_details.id) DESC LIMIT 1 ";
            ResultSet rs = QueryConnection.makeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                int count = rs.getInt("count(order_details.id)");
                System.out.println("Most ordered dish:\n" + name + " (Count: " + count + ")");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void queryOrdersPerDish() {
        try {
            String query = "SELECT dishes.name, count(order_details.id) " +
                    // not sure if i understood assignment correctly: what is the criteria (X) for how often (X) was ordered? here: X = dishes
                    "FROM order_details INNER JOIN dishes ON dishes.id = order_details.dish_id GROUP BY dish_id ORDER BY count(order_details.id) DESC";
            ResultSet rs = QueryConnection.makeQuery(query);
            System.out.println("Orders per dish:");
            while (rs.next()) {
                String name = rs.getString("name");
                int count = rs.getInt("count(order_details.id)");
                System.out.println(count + "x: " + name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
