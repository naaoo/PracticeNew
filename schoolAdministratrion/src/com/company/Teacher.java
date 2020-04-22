package com.company;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Teacher extends Person{
    private static DatabaseConnector dbConnector = new DatabaseConnector();

    @Override
    public void useSystem() {
        Scanner intScanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?\n1: display my courses\n2: display student list of a course" +
                    "\n3: grade students\n4: change password\n5: log out");
            int mode = intScanner.nextInt();
            switch (mode) {
                case 1: displayMyCourses(); break;
                case 2: printStudentList(0); break;
                case 3: gradeStudent(); break;
                case 4: changePassword(); break;
                case 5: running = false; break;
                default: System.out.println("Not a valid option"); break;
            }
        }
    }

    private void displayMyCourses() {
        ResultSet rs = dbConnector.fetchData("SELECT * FROM course WHERE teacher_id = " + Main.user.id);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private boolean printStudentList(int courseId) {
        Scanner intScanner = new Scanner(System.in);
        if (courseId == 0) {
            System.out.println("Please enter a course below");
            displayCourses();
            courseId = intScanner.nextInt();
        }
        if (Main.adminSystem.coursesArr.get(courseId - 1).teacher.id != Main.user.id) {
            System.out.println("You don't have permission to do this");
            return false;
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
                    System.out.print("(ID: " + id + ") " + name + ", Grade: " );
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
        return true;
    }

    private void gradeStudent() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("What course do you want to grade? (Please enter below)");
        displayMyCourses();
        int courseId = intScanner.nextInt();
        boolean hasPermission = printStudentList(courseId);
        if (!hasPermission) {
            return;
        }
        System.out.println("Which student do you want to grade? (Enter ID)");
        int studentId = intScanner.nextInt();
        System.out.println("Grade?");
        int grade = intScanner.nextInt();
        dbConnector.update("UPDATE course_student SET grade = " + grade +
                " WHERE course_id = " + courseId + " AND student_id = " + studentId);
    }


    public Teacher(int id, String firstName, String lastName, String password, Role role) {
        super(id, firstName, lastName, password, role);
    }
}
