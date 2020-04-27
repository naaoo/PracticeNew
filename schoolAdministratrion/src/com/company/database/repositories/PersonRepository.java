package com.company.database.repositories;

import com.company.Main;
import com.company.database.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository{
    private DatabaseConnector dbConnector;
    public ArrayList<Person> personsArr = new ArrayList<>();

    public PersonRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
    }

    @Override
    public List findAll() {
        ArrayList<Person> persons = new ArrayList<>();
        String queryUser = "SELECT * FROM person";
        ResultSet rs = dbConnector.fetchData(queryUser);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                Role role = Role.valueOf(rs.getString("role"));
                Person person = null;
                switch (role) {
                    case ADMINISTRATOR:
                        person = new Administrator(id, firstName, lastName, password, role);
                        break;
                    case STUDENT:
                        person = new Student(id, firstName, lastName, password, role);
                        break;
                    case TEACHER:
                        person = new Teacher(id, firstName, lastName, password, role);
                        break;
                }
                persons.add(person);
            }
            dbConnector.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.personsArr = persons;
        return persons;
    }

    // could be found in database as well, but to me it makes more sense to find it in existing repo,
    // so no duplicates are created
    @Override
    public Object findOne(int id) {
        Person person = Main.personRepo.personsArr.get(id);
        return person;
    }

    @Override
    public void create(Object entity) {
        Person person = (Person) entity;
        String lName = person.lastName;
        String fName = person.firstName;
        Role role = person.role;
        dbConnector.insert("INSERT INTO person (last_name, first_name, role) " +
                "VALUES ('" + lName + "', '" + fName + "', '" + role.toString() + "');");
        System.out.println("Person created with default password (1234)");
        Main.personRepo.findAll();

    }
}
