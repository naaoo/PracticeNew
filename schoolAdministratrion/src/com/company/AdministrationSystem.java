package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministrationSystem {
    private DatabaseConnector dbConnector = new DatabaseConnector();
    ArrayList<Person> personsArr;
    ArrayList<Course> coursesArr;

    public void fetchUserData() {
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
    }

    public void fetchCourseData() {
        ArrayList<Course> courses = new ArrayList<>();
        String queryUser = "SELECT * FROM course";
        ResultSet rs = dbConnector.fetchData(queryUser);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int seats = rs.getInt("max_amount_seats");
                int teacherId = rs.getInt("teacher_id");
                Person teacher = this.personsArr.get(teacherId - 1);
                Course course = new Course(id, name, seats, teacher);
                courses.add(course);
                this.coursesArr = courses;
            }
            dbConnector.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public AdministrationSystem() {
    }
}

