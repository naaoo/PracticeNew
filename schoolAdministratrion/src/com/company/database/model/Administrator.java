package com.company.database.model;

import com.company.database.repositories.DatabaseConnector;
import com.company.Main;
import com.company.database.repositories.PersonRepository;

import java.util.Scanner;

public class Administrator extends Person{

    public Administrator(int id, String firstName, String lastName, String password, Role role) {
        super(id, firstName, lastName, password, role);
    }
}
