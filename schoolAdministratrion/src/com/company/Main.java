package com.company;

import java.util.ArrayList;

public class Main {

    static AdministrationSystem adminSystem = new AdministrationSystem();
    static Person user = null;

    public static void main(String[] args) {


        adminSystem.fetchUserData();
        adminSystem.fetchCourseData();


        boolean logInFailed = true;
        while (logInFailed) {
            user = Person.logIn();
            if (user != null) {
                logInFailed = false;
            }
        }
        // depending on role, different methods are offered:
        user.useSystem();





    }
}
