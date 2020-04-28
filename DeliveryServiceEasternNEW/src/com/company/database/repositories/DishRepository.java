package com.company.database.repositories;

import com.company.Main;
import com.company.database.model.Dish;
import com.company.database.model.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishRepository implements Repository{
    private DatabaseConnector dbConnector;
    public ArrayList<Dish> dishArr = new ArrayList<>();

    public DishRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
    }

    @Override
    public List findAll() {
        ArrayList<Dish> dishes = new ArrayList<>();
        String queryDishes = "SELECT * FROM dishes";
        ResultSet rs = dbConnector.fetchData(queryDishes);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = Math.round(rs.getDouble("price") * 100.0) / 100.0;

                Dish dish = new Dish(id, name, price);
                dish.ingredientsArr = getDishIngredients(dish);
                dishes.add(dish);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnector.closeConnection();
        this.dishArr = dishes;
        return dishes;
    }

    // fills a certain dish with its ingredients
    private ArrayList<Ingredient> getDishIngredients(Dish dish) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        try {
            String queryIngredients = "SELECT ingredient_id FROM dishes_ingredients WHERE dish_name = '" +
                    dish.name + "'";
            ResultSet rs = dbConnector.fetchData(queryIngredients);
            while (rs.next()) {
                int ingredientId = rs.getInt("ingredient_id");
                // iterates IngredientArr, checks if fit in ingredientId fits and adds to ingredientArr
                for (Ingredient ingredient : Main.controller.ingredientRepository.ingredientArr) {
                    if (ingredient.id == ingredientId) {
                        ingredients.add(ingredient);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public void create(Object entity) {

    }
}
