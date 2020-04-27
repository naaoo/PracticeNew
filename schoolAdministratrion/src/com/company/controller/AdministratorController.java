package com.company.controller;

import com.company.Main;
import com.company.database.model.Course;
import com.company.database.model.Person;
import com.company.database.model.Role;
import com.company.database.model.Teacher;
import com.company.database.repositories.DatabaseConnector;
import com.company.view.View;

import java.util.Scanner;

public class AdministratorController extends PersonController{
    private static DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    @Override
    public void startSystem() {
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
        Teacher teacher = (Teacher) Main.personRepo.personsArr.get(teacherId - 1);
        Course course = new Course(null, name, capacity, teacher);
        Main.courseRepo.create(course);
    }

    private void changeTeacher() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("Which courses' teacher do you want to change? (Please enter below)");
        View.displayCourses();
        int courseId = intScanner.nextInt();
        System.out.println("Who shall be the courses' new teacher?");
        displayTeachers();
        int teacherId = intScanner.nextInt();
        dbConnector.update("UPDATE course SET teacher_id = '" + teacherId + "' WHERE course.id = " + courseId);
        Main.courseRepo.findAll();
    }

    private void displayTeachers() {
        for (Person person : Main.personRepo.personsArr) {
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
        Person person = new Person(null, fName, lName, null, role);
        Main.personRepo.create(person);
    }

}
