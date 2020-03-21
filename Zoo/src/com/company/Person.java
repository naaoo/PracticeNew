package com.company;

public class Person {
    String name;
    int age;
    String gender;

    public Person(Zoo zoo, String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        zoo.employeeArr[zoo.amountEmployee] = this;
        zoo.amountEmployee++;
    }

    @Override
    public String toString() {
        return name;
    }
}
