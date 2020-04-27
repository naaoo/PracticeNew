package com.company.controller;

import com.company.Main;
import com.company.database.repositories.DatabaseConnector;
import com.company.view.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TeacherController extends PersonController{
    private static DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    @Override
    public void startSystem() {
        Scanner intScanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?\n1: display my courses\n2: display student list of a course" +
                    "\n3: grade students\n4: change password\n5: log out");
            int mode = intScanner.nextInt();
            switch (mode) {
                case 1: View.displayMyCoursesTeacher(); break;
                case 2: View.printStudentList(); break;
                case 3: gradeStudent(); break;
                case 4: changePassword(); break;
                case 5: running = false; break;
                default: System.out.println("Not a valid option"); break;
            }
        }
    }

    public static boolean checkPermission(int courseId) {
        if (Main.courseRepo.coursesArr.get(courseId - 1).teacher.id != Main.user.id) {
            System.out.println("You don't have permission to do this");
            return false;
        } else {
            return true;
        }
    }

    private void gradeStudent() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("What course do you want to grade? (Please enter below)");
        View.displayMyCoursesTeacher();
        int courseId = intScanner.nextInt();
        boolean hasPermission = checkPermission(courseId);
        if (!hasPermission) {
            return;
        }
        System.out.println("Which student do you want to grade? (Enter ID)");
        View.printStudentList(courseId);
        int studentId = intScanner.nextInt();
        System.out.println("Grade?");
        int grade = intScanner.nextInt();
        dbConnector.update("UPDATE course_student SET grade = " + grade +
                " WHERE course_id = " + courseId + " AND student_id = " + studentId);
    }
}
