package com.company.controller;

import com.company.Main;
import com.company.database.model.Person;
import com.company.database.repositories.DatabaseConnector;
import com.company.view.View;

import java.util.Scanner;

public class StudentController extends PersonController{
    private static DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    public void startSystem() {
        Scanner intScanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?\n1: display all courses\n2: register for course" +
                    "\n3: display registered courses\n4: change password\n5: log out");
            int mode = intScanner.nextInt();
            switch (mode) {
                case 1: View.displayCourses(); break;
                case 2: register(Main.user); break;
                case 3: View.displayMyCoursesStudent(); break;
                case 4: changePassword(); break;
                case 5: running = false; break;
                default: System.out.println("Not a valid option"); break;
            }
        }
    }

    private void register(Person user) {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("What course do you want to attend? (Please enter ID below)");
        View.displayCourses();
        int courseId = intScanner.nextInt();
        dbConnector.insert("INSERT INTO course_student (course_id, student_id) " +
                "VALUES ('" + courseId + "', '" + user.id + "');");
    }

}
