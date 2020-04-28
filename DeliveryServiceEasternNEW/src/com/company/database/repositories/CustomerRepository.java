package com.company.database.repositories;

import com.company.database.model.Customer;

import java.sql.*;

// does not implement Interface Repository, since methods are used differently
public class CustomerRepository {

    public static Customer retrieveCustomerData(String firstName, String lastName) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        Customer customer = null;
        int id;
        String locationName;
        double locationPrice;
        String password;
        int amountPastOrders;
        double discountRate;
        try {
            String queryCustomerData = "SELECT * FROM " +
                    "((customers LEFT JOIN discounts ON customers.past_orders > discounts.needed_orders) " +
                    "INNER JOIN locations ON customers.location = locations.name) " +
                    "WHERE customers.first_name = '" + firstName + "' AND customers.last_name = '" + lastName +
                    "' ORDER BY percentage DESC LIMIT 1 ";
            ResultSet rs = dbConnector.fetchData(queryCustomerData);
            while (rs.next()) {
                id = rs.getInt("id");
                locationName = rs.getString("name");
                locationPrice = Math.round(rs.getDouble("price") * 100.0) / 100.0;
                password = rs.getString("password");
                amountPastOrders = rs.getInt("past_orders");
                discountRate = rs.getDouble("percentage") / 100.0;
                customer = new Customer(id, firstName, lastName, locationName, locationPrice, password, amountPastOrders, discountRate);
            }

        } catch (SQLException ex) {
            System.out.println("Customer data not successfully retrieved!");
            ex.printStackTrace();
        }
        return customer;
    }

    // checks with most actual data
    public static boolean checkSignedUp(String firstName, String lastName) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        boolean isSignedUp = false;
        ResultSet rs;
        try {
            String selectCustomers = "SELECT * FROM customers";
            rs = dbConnector.fetchData(selectCustomers);
            while (rs.next()) {
                if (rs.getString("last_name").equals(lastName) && rs.getString("first_name").equals(firstName)) {
                    isSignedUp = true;
                }
            }
            dbConnector.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isSignedUp;
    }

    public static void create(String firstName, String lastName, String location, String password) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String insertUser = "INSERT INTO customers (last_name, first_name, location, password) " +
                "VALUES ('" + lastName + "', '" + firstName + "', '"
                + location + "', '" + password + "')";
        dbConnector.insert(insertUser);
    }
}
