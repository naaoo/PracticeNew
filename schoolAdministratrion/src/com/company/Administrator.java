package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends Person{
    private static DatabaseConnector dbConnector = new DatabaseConnector();


    @Override
    public void useSystem() {
        Scanner intScanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?\n1: create course\n2: change courses' teacher" +
                    "\n3: add new person\n4: change password\n5: log out");
            int mode = intScanner.nextInt();
            switch (mode) {
                case 1: createCourse(); break;
                case 2: changeTeacher(); break;
                case 3: addPerson(); break;
                case 4: changePassword(); break;
                case 5: running = false; break;
                default: System.out.println("Not a valid option"); break;
            }
        }
    }

    private void createCourse() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        System.out.println("Courses' name:");
        String name = stringScanner.nextLine();
        System.out.println("Attendance capacity:");
        int capacity = intScanner.nextInt();
        System.out.println("Teacher (Please type in ID below):");
        displayTeachers();
        int teacherId = intScanner.nextInt();
        Teacher teacher = (Teacher)Main.adminSystem.personsArr.get(teacherId - 1);
        dbConnector.insert("INSERT INTO course (name, max_amount_seats, teacher_id)" +
                "VALUES ('" + name + "', '" + capacity + "', '" + teacherId + "');");
        Main.adminSystem.fetchCourseData();
    }

    private void changeTeacher() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("Which courses' teacher do you want to change? (Please enter below)");
        displayCourses();
        int courseId = intScanner.nextInt();
        System.out.println("Who shall be the courses' new teacher?");
        displayTeachers();
        int teacherId = intScanner.nextInt();
        dbConnector.update("UPDATE course SET teacher_id = '" + teacherId + "' WHERE course.id = " + courseId);
        Main.adminSystem.fetchCourseData();
    }

    private void displayTeachers() {
        for (Person person : Main.adminSystem.personsArr) {
            if (person.role.equals(Role.TEACHER)) {
                System.out.println(person.id + ": " + person.firstName + " " + person.lastName);
            }
        }
    }

    private void addPerson() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        System.out.println("First name:");
        String fName = stringScanner.nextLine();
        System.out.println("Last name:");
        String lName = stringScanner.nextLine();
        System.out.println("Role:\n1: Administrator\n2: Student\n3: Teacher");
        Role role = Role.values()[intScanner.nextInt() - 1];
        dbConnector.insert("INSERT INTO person (last_name, first_name, role) " +
                "VALUES ('" + lName + "', '" + fName + "', '" + role.toString() + "');");
        System.out.println("Person created with default password (1234)");
        Main.adminSystem.fetchUserData();
    }

    public Administrator(int id, String firstName, String lastName, String password, Role role) {
        super(id, firstName, lastName, password, role);
    }
}
