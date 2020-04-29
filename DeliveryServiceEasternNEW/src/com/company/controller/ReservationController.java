package com.company.controller;

import com.company.Main;
import com.company.database.model.Reservation;
import com.company.database.model.Table;
import com.company.database.repositories.ReservationRepository;
import com.company.view.View;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationController {
    static DateTimeFormatter tf = DateTimeFormat.forPattern("HH:mm");
    static String openingT = "16:00";
    static String lastRes = "22:00";
    static LocalTime openingTime = tf.parseLocalTime(openingT);
    static LocalTime lastReservation = tf.parseLocalTime(lastRes);

    public static void makeReservation() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        DateTimeFormatter df = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
        ArrayList<Table> freeTables = cloneTablesArr();
        System.out.println("Please enter day (Format: DD-MM-YYYY):");
        String date = stringScanner.nextLine();
        String time = "";
        boolean inOpeningHours = false;
        while (!inOpeningHours) {
            System.out.println("Reservations possible between " + openingT + " and " + lastRes
                    + "\nPlease enter time (Format: HH:MM):");
            time = stringScanner.nextLine();
            LocalTime timeReservation = tf.parseLocalTime(time);
            if (timeReservation.isAfter(openingTime) && timeReservation.isBefore(lastReservation)) {
                inOpeningHours = true;
            } else {
                System.out.println("This is not feasible (Consider our opening hours again)");
            }
        }
        DateTime dateTimeReservation = df.parseDateTime(date + " " + time);
        System.out.println("How many persons?");
        int seats = intScanner.nextInt();
        freeTables = removeReservedTables(freeTables, dateTimeReservation);
        freeTables = removeSmallerTables(freeTables, seats);
        if (freeTables.size() > 0) {
            System.out.println("Please choose one of our free tables at that time (type in number below):");
            View.displayTables(freeTables);
            int tableId = intScanner.nextInt();
            Reservation reservation = new Reservation(null, Main.controller.customer.id, tableId, seats,
                    dateTimeReservation, dateTimeReservation.plusHours(1).plusMinutes(30));
            Main.controller.reservationRepository.create(reservation);
            System.out.println("Reservation successful");
        } else {
            System.out.println("Sorry, at that time there are no suitable tables open. Please try again at another time.");
        }
    }

    // if tables' maxSeats are lower than seats, table is subtracted form freeTables
    private static ArrayList<Table> removeSmallerTables(ArrayList<Table> freeTables, int seats) {
        for (Table table : freeTables) {
            if (table.maxSeats < seats) {
                freeTables.remove(table);
            }
        }
        return freeTables;
    }

    // if start of a given reservation is rivaling existing reservation
    // concerned table is subtracted from freeTables
    private static ArrayList<Table> removeReservedTables(ArrayList<Table> freeTables, DateTime timeReservation) {
        for (Reservation reservation : Main.controller.reservationRepository.reservationArr) {
            if ((timeReservation.isBefore(reservation.timeStart) &&
                    !timeReservation.plusHours(1).plusMinutes(30).isBefore(reservation.timeStart))
                    || (timeReservation.isAfter(reservation.timeStart)
                    && timeReservation.isBefore(reservation.timeEnd)) || timeReservation.equals(reservation.timeStart)) {
                freeTables.removeIf(table -> table.id == reservation.tableId);
            }
        }
        return freeTables;
    }

    private static ArrayList<Table> cloneTablesArr() {
        ArrayList<Table> tableArr = new ArrayList<>();
        for (Table table : Main.controller.tableRepository.tablesArr) {
            try {
                tableArr.add(table.clone());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
        return tableArr;
    }

    public static void cancelReservation() {
        Scanner intScanner = new Scanner(System.in);
        System.out.println("Please select the reservation you want to cancel by typing in its' ID below\n" +
                "(type in '0' if you don't want to delete any");
        View.displayMyReservations();
        int id = intScanner.nextInt();
        if (id == 0) {
            return;
        } else {
            Main.controller.reservationRepository.cancel(id);
        }
    }
}
