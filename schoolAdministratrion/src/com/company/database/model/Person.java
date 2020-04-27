package com.company.database.model;

import com.company.database.repositories.DatabaseConnector;
import com.company.Main;

import java.util.Scanner;

public class Person {

    public Integer id;
    public String firstName;
    public String lastName;
    public String password;
    public Role role;

    public Person(Integer id, String firstName, String lastName, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;

    }
}
