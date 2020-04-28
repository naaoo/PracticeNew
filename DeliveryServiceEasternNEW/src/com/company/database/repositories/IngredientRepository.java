package com.company.database.repositories;

import com.company.database.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientRepository implements Repository {
    private DatabaseConnector dbConnector;
    public ArrayList<Ingredient> ingredientArr = new ArrayList<>();

    public IngredientRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
    }


    @Override
    public List findAll() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String queryIngredients = "SELECT * FROM ingredients";
        ResultSet rs = dbConnector.fetchData(queryIngredients);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = Math.round(rs.getDouble("price") * 100.0) / 100.0;
                ingredients.add(new Ingredient(id, name, price));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnector.closeConnection();
        this.ingredientArr = ingredients;
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
