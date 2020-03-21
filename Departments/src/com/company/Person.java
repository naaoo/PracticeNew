package com.company;

public class Person {
    String firstName;
    String lastName;
    String gender;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public Person(String firstName, String lastName, String gender) {
        if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("male")) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
        } else {
            System.out.println("no known gender");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
