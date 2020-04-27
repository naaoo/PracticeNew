package com.company.view;

import com.company.Main;
import com.company.controller.TeacherController;
import com.company.database.repositories.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class View {
    private static DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    public static void displayCourses() {
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
                System.out.println(id + ": " + name + " (Teacher: " + Main.personRepo.personsArr.get(teacherId - 1).lastName +
                        "), " + freeSeats + "/" + maxSeats + " seats open");
            }
            System.out.println();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnector.closeConnection();
    }

    public static void displayMyCoursesTeacher() {
        ResultSet rs = dbConnector.fetchData("SELECT * FROM course WHERE teacher_id = " + Main.user.id);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            System.out.println();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void displayMyCoursesStudent() {
        ResultSet rs = dbConnector.fetchData("SELECT course.id, name, teacher_id, grade FROM course " +
                "INNER JOIN course_student ON course.id = course_student.course_id " +
                "WHERE course_student.student_id = " + Main.user.id);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int teacherId = rs.getInt("teacher_id");
                int grade = rs.getInt("grade");
                System.out.print("Course: " + name + " (ID " + id + ") (Teacher: " +
                        Main.personRepo.personsArr.get(teacherId).lastName + ") Grade: ");
                if (grade == 0) {
                    System.out.println("-");
                } else {
                    System.out.println(grade);
                }
            }
            System.out.println();
            dbConnector.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void printStudentList() {
        Scanner intScanner = new Scanner(System.in);

        System.out.println("Please enter a course below");
        // could also displayMyCourses, then permission check below wouldn't be necessarily needed
        View.displayCourses();
        int courseId = intScanner.nextInt();
        printStudentList(courseId);
    }

    public static void printStudentList(int courseId) {
        if (!TeacherController.checkPermission(courseId)) {
            return;
        } else {
            ResultSet rs = dbConnector.fetchData("SELECT person.id, last_name, first_name, grade" +
                    " FROM ((course INNER JOIN course_student " +
                    "ON course.id = course_student.course_id) " +
                    "INNER JOIN person ON course_student.student_id = person.id) " +
                    "WHERE course.id = " + courseId + " GROUP BY person.id");
            try {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("last_name") + ", " + rs.getString("first_name");
                    int grade = rs.getInt("grade");
                    System.out.print("(ID: " + id + ") " + name + "; Grade: " );
                    if (grade == 0) {
                        System.out.println("-");
                    } else {
                        System.out.println(grade);
                    }
                }
                System.out.println();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
