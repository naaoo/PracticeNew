package com.company.database.model;

import java.util.ArrayList;

public class Order{
    public ArrayList<Dish> dishes = new ArrayList<>();
    public double costsPure;
    public double costsAfterDiscount;
    public double deliveryCosts;
    public double costsOverall;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
