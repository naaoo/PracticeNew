package com.company.database.repositories;

import com.company.Main;
import com.company.database.model.Dish;
import com.company.database.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishRepository implements Repository{
    private DatabaseConnector dbConnector;
    public ArrayList<Dish> dishesArr = new ArrayList<>();

    public DishRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
        findAll();
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
        this.dishesArr = dishes;
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
                // iterates IngredientsArr, checks if fit in ingredientId fits and adds to ingredientArr
                for (Ingredient ingredient : Main.controller.ingredientRepository.ingredientsArr) {
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
        Dish dish = (Dish)entity;
        String insertDish = "INSERT INTO dishes (name, price) " +
                "VALUES ('" + dish.name + "', '" + dish.price + "');";
        dbConnector.insert(insertDish);
    }

    public void insertDishIngredients(ArrayList<Ingredient> ingredients, Dish dish) {
        for (Ingredient ingredient : ingredients) {
            String insertIngredient = "INSERT INTO dishes_ingredients (dish_name, ingredient_id) VALUES " +
                    "('" + dish.name + "', '" + ingredient.id + "')";
            dbConnector.insert(insertIngredient);
        }
    }
}
