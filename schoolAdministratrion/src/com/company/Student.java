package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student extends Person{

    private static DatabaseConnector dbConnector = new DatabaseConnector();

    @Override
    public void useSystem() {
        Scanner intScanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?\n1: display all courses\n2: register for course" +
                    "\n3: display registered courses\n4: change password\n5: log out");
            int mode = intScanner.nextInt();
            switch (mode) {
                case 1: displayCourses(); break;
                case 2: register(); break;
                case 3: displayMyCourses(); break;
                case 4: changePassword(); break;
                case 5: running = false; break;
                default: System.out.println("Not a valid option"); break;
            }
        }
    }

    @Override
    public void displayCourses() {
        ResultSet rs = dbConnector.fetchData("SELECT course.id, name, max_amount_seats, teacher_id, " +
                "(max_amount_seats - COUNT(course_student.course_id)) AS free_seats " +
                "FROM course LEFT JOIN course_student ON course.id = course_student.course_id GROUP BY course.id ");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int maxSeats = rs.getInt("max_amount_seats");
                int teacherId = rs.getInt("teacher_id");
                int freeSeats = rs.getInt("free_seats");
                System.out.println(id + ": " + name + " (Teacher: " + Main.adminSystem.personsArr.get(teacherId - 1).lastName +
                        "), " + freeSeats + "/" + maxSeats + " seats open");
            }
            System.out.println();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnector.closeConnection();
    }

    private void register() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("What course do you want to attend? (Please enter ID below)");
        displayCourses();
        int courseId = intScanner.nextInt();
        dbConnector.insert("INSERT INTO course_student (course_id, student_id) " +
                "VALUES ('" + courseId + "', '" + this.id + "');");
    }

    private void displayMyCourses() {
        ResultSet rs = dbConnector.fetchData("SELECT course.id, name, teacher_id, grade FROM course " +
                "INNER JOIN course_student ON course.id = course_student.course_id " +
                "WHERE course_student.student_id = " + this.id);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int teacherId = rs.getInt("teacher_id");
                int grade = rs.getInt("grade");
                System.out.print("Course: " + name + " (ID " + id + ") (Teacher: " +
                        Main.adminSystem.personsArr.get(teacherId).lastName + ") Grade: ");
                if (grade == 0) {
                    System.out.println("-");
                } else {
                    System.out.println(grade);
                }
            }
            dbConnector.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Student(int id, String firstName, String lastName, String password, Role role) {
        super(id, firstName, lastName, password, role);

    }
}
