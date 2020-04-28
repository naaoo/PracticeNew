package com.company.controller;

import com.company.database.repositories.DatabaseConnector;
import com.company.database.repositories.QueryConnection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Exporter {
    static DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    public static void exportOrders() {
        ResultSet rs;
        File orders = new File("C:\\Users\\David\\Desktop\\Orders.csv");
        try {
            // only creates new file if not already existing, false for overwrite
            orders.createNewFile();
            FileWriter writer = new FileWriter(orders, false);
                String query = "SELECT orders.id AS orderId, customers.id AS customerId, orders.costs_overall " +
                        "FROM orders INNER JOIN customers ON orders.customer_id = customers.id ";
                rs = QueryConnection.makeQuery(query);
                writer.write("OrderId;CustomerId;PriceOverall");
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    int customerId = rs.getInt("customerId");
                    double costs = Math.round(rs.getDouble("costs_overall") * 100.0) / 100.0;
                    writer.write("\n" + orderId + ";" + customerId + ";" + costs);
                }
                System.out.println("Export completed");
            writer.close();
            dbConnector.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void exportIngredientConsumption() {
        File consumption = new File("C:\\Users\\David\\Desktop\\Consumption.csv");
        try {
            // only creates new file if not already existing, false for overwrite
            consumption.createNewFile();
            FileWriter writer = new FileWriter(consumption, false);
            writer.write("IngredientId;IngredientName;Amount");
            try {
                String detailCount = "SELECT ingredients.id, ingredients.name, COUNT(ingredients.id) AS count " +
                        "FROM (((order_details INNER JOIN dishes ON order_details.dish_id = dishes.id) " +
                        "INNER JOIN dishes_ingredients ON dishes.name = dishes_ingredients.dish_name) " +
                        "INNER JOIN ingredients ON dishes_ingredients.ingredient_id = ingredients.id) " +
                        "GROUP BY ingredients.id";
                ResultSet rsDetailCount = QueryConnection.makeQuery(detailCount);
                String changes = "SELECT kind, ingredients.id, ingredients.name, COUNT(ingredient_id) " +
                        "FROM order_changes INNER JOIN ingredients ON order_changes.ingredient_id = ingredients.id " +
                        "GROUP BY ingredients.id, kind";
                ResultSet rsChanges = QueryConnection.makeQuery(changes);
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
}
