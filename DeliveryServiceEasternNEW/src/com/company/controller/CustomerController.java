package com.company.controller;

import com.company.database.model.Customer;
import com.company.database.repositories.CustomerRepository;
import java.util.Scanner;

public class CustomerController {

    public static Customer logIn() {
        Scanner stringScanner = new Scanner(System.in);
        Customer customer;
        // get data
        System.out.println("Welcome to our delivery service!\nPlease enter the following:\nFirst name:");
        String firstName = stringScanner.next();
        System.out.println("Last name:");
        String lastName = stringScanner.next();
        if (CustomerRepository.checkSignedUp(firstName, lastName)) {
            // get Data
            customer = CustomerRepository.retrieveCustomerData(firstName, lastName);
            // check blocked
            if (checkIfBlocked(customer)) {
                System.out.println("Sorry but your account has been blocked. Please contact the administrator.");
                return null;
            }
            System.out.println("Welcome back " + firstName + "!");
            //complete login
            int tries = 3;
            while (tries != 0) {
                System.out.println("Password:");
                String password = stringScanner.next();
                if (password.equals(customer.password)) {
                    System.out.println("Password correct");
                    break;
                } else {
                    tries--;
                    if (tries == 0) {
                        System.out.println("Log in failed!");
                        CustomerRepository.blockCustomer(customer);
                        return null;
                    } else {
                        if (tries == 1) {
                            System.out.println("Wrong password. " + tries + " try left");
                        } else {
                            System.out.println("Wrong password. " + tries + " tries left");
                        }
                    }
                }
            }
        } else {
            // signUp new customer
            System.out.println("Welcome to our delivery service " + firstName + "! Please continue to sign up:");
            signUp(firstName, lastName);
            customer = CustomerRepository.retrieveCustomerData(firstName, lastName);
        }
        return customer;
    }

    // signs new customer up and writes into database
    private static void signUp(String firstName, String lastName) {
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Location (just town, no address needed):");
        String location = stringScanner.next();
        String password = "";
        boolean isMatching = false;
        while (!isMatching) {
            System.out.println("Password:");
            password = stringScanner.next();
            System.out.println("Please enter the same password again:");
            String passwordControl = stringScanner.next();
            if (password.equals(passwordControl)) {
                isMatching = true;
            } else {
                System.out.println("Passwords didn't match. Please try again:");
            }
        }
        // write into database
        CustomerRepository.create(firstName, lastName, location, password);
    }

    private static boolean checkIfBlocked(Customer customer) {
        if (customer.isBlocked) {
            return true;
        } else {
            return false;
        }
    }
}
