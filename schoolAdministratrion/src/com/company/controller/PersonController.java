package com.company.controller;

import com.company.Main;
import com.company.database.model.Person;
import com.company.database.repositories.DatabaseConnector;

import java.util.Scanner;

public class PersonController{
    private static DatabaseConnector dbConnector = DatabaseConnector.getInstance();
    private static StudentController studentController = new StudentController();
    private static AdministratorController administratorController = new AdministratorController();
    private static TeacherController teacherController = new TeacherController();

    // useSystem depends on each sub class (admin, student, teacher) and is defined in their specific controller
    public void startSystem() {
        switch (Main.user.role) {
            case ADMINISTRATOR: administratorController.startSystem(); break;
            case STUDENT: studentController.startSystem(); break;
            case TEACHER: teacherController.startSystem(); break;
        }
    }

    public static Person logIn() {
        Person user = null;
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Please log in:\nWhat's your name? (Format: FirstName LastName)");
        String fullName = stringScanner.nextLine();
        String[] splitName = fullName.split(" ");
        String firstName = splitName[0];
        String lastName = splitName[1];
        for (Person person : Main.personRepo.personsArr) {
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
        Main.personRepo.findAll();
    }

}
