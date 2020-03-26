package com.company;

import java.util.ArrayList;

public class Person {
    String name;
    Department department;
    public static ArrayList<Person> persons = new ArrayList<>();

    public Person(String name, Department department) {
        this.name = name;
        this.department = department;
        department.employees.add(this);
        persons.add(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
