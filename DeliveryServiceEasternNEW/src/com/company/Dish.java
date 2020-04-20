package com.company;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Dish implements Cloneable{
    int id;
    String name;
    double price;
    ArrayList<Ingredient> ingredientsArr = new ArrayList<>();
    ArrayList<Ingredient> additionsArr = new ArrayList<>();
    ArrayList<Ingredient> subtractionsArr = new ArrayList<>();

    public void customizeDish() {
        Scanner intScanner = new Scanner(System.in);
        int mode = 0;
        while (mode != 3) {
            System.out.println("1: add ingredient\n2: subtract ingredient\n3: finished");
            mode = intScanner.nextInt();
            switch (mode) {
                case 1 : // add
                    System.out.println("Please enter the ingredients' number below:");
                    Ingredient.displayAllIngredients();
                    int ingredientAddPos = intScanner.nextInt() - 1;
                    Ingredient ingredientAdd = null;
                    try {
                        ingredientAdd = Main.allIngredientsArr.get(ingredientAddPos).clone();
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    this.ingredientsArr.add(ingredientAdd);
                    this.additionsArr.add(ingredientAdd);
                    this.price = Math.round((this.price + ingredientAdd.price) * 100.0) / 100.0;
                    break;
                case 2 : // sub
                    System.out.println("Please enter the ingredients' number below:");
                    this.displayIngredients();
                    int ingredientSubPos = intScanner.nextInt() - 1;
                    Ingredient ingredientSub = null;
                    try {
                        ingredientSub = this.ingredientsArr.get(ingredientSubPos).clone();
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    this.ingredientsArr.remove(ingredientSubPos);
                    this.subtractionsArr.add(ingredientSub);
                    break;
            }
        }
    }

    public static void displayOriginalDishes() {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Dish dish : Main.allOriginalDishesArr) {
            System.out.println(i + ": " + dish.name + " (" + df.format(dish.price) + "€)");
            i++;
        }
    }

    public void displayIngredients() {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Ingredient ingredient : this.ingredientsArr) {
            System.out.println(i + ": " + ingredient.name);
            i++;
        }
    }

    public static void retrieveDishes() {
        try {
            Connection conn = DriverManager.getConnection(Main.databaseUrl);
            Statement stmt = conn.createStatement();
            String queryDishes = "SELECT * FROM dishes";
            ResultSet rs = stmt.executeQuery(queryDishes);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = Math.round(rs.getDouble("price") * 100.0) / 100.0;
                Main.allOriginalDishesArr.add(new Dish(id, name, price));
                // fills ingredientsArr of last constructed dish
                Main.allOriginalDishesArr.get(Main.allOriginalDishesArr.size() - 1).fillDishIngredients();
            }
            stmt.close();
            conn.close();
        } catch ( SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillDishIngredients() {
        try {
            Connection conn1 = DriverManager.getConnection(Main.databaseUrl);
            Statement stmt1 = conn1.createStatement();
            String queryIngredients = "SELECT dish_name, ingredient_id FROM dishes_ingredients ORDER BY id ASC";
            ResultSet rs = stmt1.executeQuery(queryIngredients);
            while (rs.next()) {
                String compareName = rs.getString("dish_name");
                int ingredientId = rs.getInt("ingredient_id");
                // check if row in rs fits dish
                if (compareName.equals(this.name)) {
                    // iterates allIngredientsArr, checks if fit in ingredientId fits and adds to ingredientsArr
                    for (Ingredient ingredient : Main.allIngredientsArr) {
                        if (ingredient.id == ingredientId) {
                            this.ingredientsArr.add(ingredient);
                        }
                    }
                }
            }
            stmt1.close();
            conn1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    protected Dish clone() throws CloneNotSupportedException {
        return (Dish)super.clone();
    }
}
