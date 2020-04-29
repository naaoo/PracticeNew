package com.company;

import com.company.controller.Controller;
import com.company.database.model.Reservation;
import com.company.view.View;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Main {

    // database: tables are plural, should be singular but i was too far so i went with it ;-)

    public static Controller controller = new Controller();

    public static void main(String[] args) {

        controller.ownerProgram();
    }


}
