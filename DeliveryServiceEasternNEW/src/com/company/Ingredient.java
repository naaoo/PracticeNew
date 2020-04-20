package com.company;

import java.sql.*;
import java.text.DecimalFormat;

public class Ingredient implements Cloneable{
    int id;
    String name;
    double price;

    // retrieves all ingredients from database and saves into Main.allIngredientArr
    public static void retrieveAll() {
        try {
            Connection conn = DriverManager.getConnection(Main.databaseUrl);
            Statement stmt = conn.createStatement();
            String queryIngredients = "SELECT * FROM ingredients";
            ResultSet rs = stmt.executeQuery(queryIngredients);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = Math.round(rs.getDouble("price") * 100.0) / 100.0;
                Main.allIngredientsArr.add(new Ingredient(id, name, price));
            }
            stmt.close();
            conn.close();
        } catch ( SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void displayAllIngredients() {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Ingredient ingredient : Main.allIngredientsArr) {
            System.out.println(i + ": " + ingredient.name + " (" + df.format(ingredient.price) + "€)");
            i++;
        }
    }

    private Ingredient(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    protected Ingredient clone() throws CloneNotSupportedException {
        return (Ingredient)super.clone();
    }
}
