package com.company;

public class Driver {

    String name;
    double money;

    public Driver(String name, double money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public String toString() {
        return name;
    }
}
