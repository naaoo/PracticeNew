package com.company;

import com.company.controller.Controller;
import com.company.controller.ReservationController;

public class Main {

    public static Controller controller = new Controller();

    public static void main(String[] args) {

        //ReservationController.makeReservation();

        controller.startCustomerSystem();
    }
}
