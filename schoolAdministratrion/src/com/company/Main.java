package com.company;

import com.company.controller.PersonController;
import com.company.database.model.Person;
import com.company.database.repositories.CourseRepository;
import com.company.database.repositories.PersonRepository;

public class Main {

    public static PersonRepository personRepo = new PersonRepository();
    public static CourseRepository courseRepo = new CourseRepository();
    public static Person user = null;
    public static PersonController controller = new PersonController();

    public static void main(String[] args) {

        // fetch data
        personRepo.findAll();
        courseRepo.findAll();

        while (true) {
            boolean logInFailed = true;
            while (logInFailed) {
                user = controller.logIn();
                if (user != null) {
                    logInFailed = false;
                }
            }
            // depending on role, different methods are offered:
            controller.startSystem();
            // after use, user is set null (= logged out)
            user = null;
        }
    }
}
