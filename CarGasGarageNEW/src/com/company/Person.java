package com.company;

public class Person {
    String name;
    double money;

    public Person(String name, double money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public String toString() {
        return name;
    }
}
