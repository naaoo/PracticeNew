package com.company;

import java.sql.*;
import java.text.DecimalFormat;

public class Receipt {

    public static void printReceipt(int customerId) {
        DecimalFormat df = new DecimalFormat("#.00");
        int orderId = selectLatestOrder(customerId);
        ResultSet rsDetails = selectOrderDetails(orderId);
        try {
            while (rsDetails.next()) {
                int orderDetailsId = rsDetails.getInt("id");
                String dishName = rsDetails.getString("name");
                double price = rsDetails.getDouble("price");
                System.out.print(df.format(price) + " €: " + dishName);
                ResultSet rsChanges = selectOrderChanges(orderDetailsId);
                try {
                    if (rsChanges.next()) {
                        System.out.print(" (");
                        rsChanges.beforeFirst();
                    }
                    while (rsChanges.next()) {
                        String kind = "";
                        if (rsChanges.getString("kind").equals("sub")) {
                            kind = "-";
                        } else if (rsChanges.getString("kind").equals("add")) {
                            kind = "+";
                        }
                        String name = rsChanges.getString("name");
                        if (rsChanges.isLast()) {
                            System.out.print(kind + " " + name + ")");
                        } else {
                            System.out.print(kind + " " + name + ", ");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("----------------------------------------------------------------");
        ResultSet rsCosts = selectCosts(orderId);
        try {
            while (rsCosts.next()) {
                double costsPure = rsCosts.getDouble("costs_pure");
                double costsAfterDiscount = rsCosts.getDouble("costs_after_discount");
                double deliveryCosts = rsCosts.getDouble("delivery_costs");
                double costsOverall = rsCosts.getDouble("costs_overall");
                System.out.print(df.format(costsPure) + " €");
                if (costsPure != costsAfterDiscount) {
                    System.out.print("\n" + df.format(costsAfterDiscount) + " € (after discount)");
                }
                System.out.println(" + " + df.format(deliveryCosts) + " € delivery costs");
                System.out.println("------");
                System.out.println(df.format(costsOverall) + " € overall");
            }
        } catch (SQLException ex) {

        }
    }

    private static ResultSet selectCosts(int orderId) {
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String queryCosts = "SELECT * FROM orders WHERE id = " + orderId;
            rs = stmt.executeQuery(queryCosts);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    private static ResultSet selectOrderChanges(int orderDetailsId) {
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String queryOrderChanges = "SELECT kind,ingredients.name FROM order_changes INNER JOIN ingredients " +
                    "ON order_changes.ingredient_id = ingredients.id WHERE order_changes.order_details_id = " + orderDetailsId;
            rs = stmt.executeQuery(queryOrderChanges);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    private static ResultSet selectOrderDetails(int orderId) {
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String queryOrderDetails = "SELECT order_details.id, dishes.name, order_details.price FROM order_details " +
                    "INNER JOIN dishes ON order_details.dish_id = dishes.id WHERE order_details.order_id = " + orderId;
            rs = stmt.executeQuery(queryOrderDetails);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    private static int selectLatestOrder(int customerId) {
        int orderId = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root");
            Statement stmt = conn.createStatement();
            String queryOrderId = "SELECT id FROM orders WHERE customer_id = " + customerId + " ORDER BY id DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(queryOrderId);
            while (rs.next()) {
                orderId = rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orderId;
    }



}
