package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    private static DatabaseConnector dbConnector = new DatabaseConnector();

    int id;
    String firstName;
    String lastName;
    String password;
    Role role;

    public static Person logIn() {
        Person user = null;
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Please log in:\nWhat's your name? (Format: FirstName LastName)");
        String fullName = stringScanner.nextLine();
        String[] splitName = fullName.split(" ");
        String firstName = splitName[0];
        String lastName = splitName[1];
        for (Person person : Main.adminSystem.personsArr) {
            if (person.firstName.equalsIgnoreCase(firstName) && person.lastName.equalsIgnoreCase(lastName)) {
                user = person;
            }
        }
        if (user != null) {
            if(!passwordControl(user)) {
                System.out.println("Log in failed");
                return null;
            }
        } else {
            System.out.println("Sorry, but you're not in our system. Please contact our administrator");
        }
        return user;
    }

    private static boolean passwordControl(Person user) {
        Scanner scanner = new Scanner(System.in);
        boolean passwordCorrect = false;
        int tries = 3;
        while (!passwordCorrect && tries > 0) {
            System.out.println("Password:");
            String passwordControl = scanner.nextLine();
            if (passwordControl.equals(user.password)) {
                passwordCorrect = true;
            } else {
                tries--;
                if (tries == 1) {
                    System.out.println("Wrong password. " + tries + " try left");
                } else {
                    System.out.println("Wrong password. " + tries + " tries left");
                }
            }
        }
        return passwordCorrect;
        //Todo : could be blocked if 3times wrong (Currently logIn starts new)
    }

    public void useSystem() {
    }

    public void displayCourses() {
        for (Course course : Main.adminSystem.coursesArr) {
            System.out.println(course.id + ": " + course.name + " (Capacity: " +
                    course.maxSeats + ", Teacher: " + course.teacher.lastName + ")");
        }
    }

    public void changePassword() {
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Please enter your old password:");
        String oldPassword = stringScanner.nextLine();
        if (!oldPassword.equals(Main.user.password)) {
            System.out.println("Sorry, that's not your old password...");
        } else {
            System.out.println("New password:");
            String newPassword = stringScanner.nextLine();
            System.out.println("Please enter the same password again:");
            String passwordCheck = stringScanner.nextLine();
            if (!newPassword.equals(passwordCheck)) {
                System.out.println("Passwords, didn't match, please try again");
            } else {
                dbConnector.update("UPDATE person SET password = '" + newPassword + "' WHERE id= " + Main.user.id);
            }
        }
        Main.adminSystem.fetchUserData();
    }


    public Person(int id, String firstName, String lastName, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;

    }
}
