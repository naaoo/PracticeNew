package com.company.database.model;

import java.util.ArrayList;

public class Dish implements Cloneable{
    public int id;
    public String name;
    public double price;
    public ArrayList<Ingredient> ingredientsArr = new ArrayList<>();
    public ArrayList<Ingredient> additionsArr = new ArrayList<>();
    public ArrayList<Ingredient> subtractionsArr = new ArrayList<>();

    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Dish clone() throws CloneNotSupportedException {
        return (Dish)super.clone();
    }
}
