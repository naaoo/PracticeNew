package com.company;

public class Person {

    String name;
    int age;

    int amountPersons = 0;
    Person[] persons = new Person[100];

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        persons[amountPersons] = this;
        amountPersons++;
    }
}
