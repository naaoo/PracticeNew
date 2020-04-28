package com.company.database.model;

public class Ingredient implements Cloneable{
    public Integer id;
    public String name;
    public double price;

    public Ingredient(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Override
    public Ingredient clone() throws CloneNotSupportedException {
        return (Ingredient)super.clone();
    }
}
