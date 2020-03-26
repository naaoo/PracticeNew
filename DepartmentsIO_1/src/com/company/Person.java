package com.company;

public class Person {
    String name;
    Department department;

    public static Person[] persons = new Person[50];
    public static int personCounter = 0;

    public Person(String name, Department department) {
        this.name = name;
        this.department = department;
        addPerson(this);
    }

    public void addPerson(Person person) {
        persons[personCounter] = this;
        personCounter++;
    }
}
