package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        haveCustomer();
    }

    // whole customer process
    public static void haveCustomer() {
        Scanner scanner = new Scanner(System.in);
        int mode = 0;
        System.out.println("Welcome to our cinema!\nHow can i help you? (Please type in below)");
        while (mode != 4) {
            System.out.println("1: Display all available movie presentations\n2: Make reservation\n3: Cancel reservation\n4: Leave");
            mode = scanner.nextInt();
            switch (mode) {
                case 1: displayPresentations(); break;
                case 2: makeReservation(); break;
                case 3: cancelReservation(); break;
                case 4: System.out.println("Thank you for your visit!"); break;
            }
        }
    }

    //showPossiblePresentations
    public static void displayPresentations() {
        System.out.println("Available presentations:");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root");
            Statement stmt;
            String querySelectPresentations = "SELECT * FROM presentations INNER JOIN movies ON movies.name = movie ORDER BY time";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(querySelectPresentations);
                while (rs.next()) {
                    int id = rs.getInt("presentation_id");
                    String time = rs.getString("time");
                    String movie = rs.getString("name");
                    int emptySeats = rs.getInt("empty_seats");
                    System.out.println(id + ": " + time + "\t" + movie + "\t(" + emptySeats + " seats available)");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println();
    }

    public static void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        String makeReservation = "Y";
        System.out.println("Please select your presentation by typing in it's number:");
        int presentationId = scanner.nextInt();
        // get empty seats from database for selected presentation
        int emptySeats = getEmptySeats(presentationId);
        // next code block will be executed until user types in "N"
        while (makeReservation.equals("Y")) {
            System.out.println("How many seats would you like to book?");
            int amountSeats = scanner.nextInt();
            // check if there's enough seats open
            boolean hasEnoughSeats = checkEnoughSeatsOpen(amountSeats, emptySeats);
            if (hasEnoughSeats) {
                Connection conn;
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root");
                    Statement stmt = conn.createStatement();
                    // for some reason i got an "SQLSyntax error when executing both SQL Statements at the same time, therefore i split them...
                    String insertReservation = "INSERT INTO reservations (presentation_id, amount_seats) VALUES (" + presentationId + ", " + amountSeats + ");";
                    String updatePresentation = "UPDATE presentations SET empty_seats = (empty_seats - " + amountSeats + ") WHERE presentation_id = " + presentationId + ";";
                    stmt.executeUpdate(insertReservation);
                    stmt.executeUpdate(updatePresentation);
                    makeReservation = "N";
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // give back reservationNumber
                System.out.println("Your reservation number is: " + getReservationNumber() + " (Required for cancellation)\n");
            } else {
                System.out.println("Sorry, there are not enough seats open.\nWould you like to book a lower number of seats (Max available: " + emptySeats + ")? (Y/N)");
                makeReservation = stringScanner.nextLine().toUpperCase();
            }
        }
    }

    private static int getReservationNumber() {
        int reservationNumber = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root");
            Statement stmt = conn.createStatement();
            String selectReservationNumber = "SELECT * FROM reservations ORDER BY reservation_id DESC  LIMIT 1";
            ResultSet rs = stmt.executeQuery(selectReservationNumber);
            while (rs.next()) {
                reservationNumber = rs.getInt("reservation_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservationNumber;
    }

    private static boolean checkEnoughSeatsOpen(int amountSeats, int emptySeats) {
        if (emptySeats < amountSeats) {
            return false;
        } else {
            return true;
        }
    }

    private static int getEmptySeats(int presentationId) {
        int emptySeats = 0;
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root");
            String queryAvailableSeats = "SELECT empty_seats FROM presentations WHERE presentation_id = " + presentationId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryAvailableSeats);
            while (rs.next()) {
                emptySeats = rs.getInt("empty_seats");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return emptySeats;
    }

    public static void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your reservation number:");
        int reservationNumber = scanner.nextInt();
        int reservedSeats = getReservedSeats(reservationNumber);
        int presentationId = getPresentationId(reservationNumber);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root");
            Statement stmt = conn.createStatement();
            String updatePresentation = "UPDATE presentations SET empty_seats = (empty_seats + " + reservedSeats + ") WHERE presentation_id = " + presentationId;
            String deleteReservation = "DELETE FROM reservations WHERE reservation_id = " + reservationNumber;
            stmt.executeUpdate(updatePresentation);
            stmt.executeUpdate(deleteReservation);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Your presentation has been successfully deleted\n");
    }

    private static int getReservedSeats(int reservationNumber) {
        int reservedSeats = 0;
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root");
            String queryAvailableSeats = "SELECT amount_seats FROM reservations WHERE reservation_id = " + reservationNumber;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryAvailableSeats);
            while (rs.next()) {
                reservedSeats = rs.getInt("amount_seats");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservedSeats;
    }

    private static int getPresentationId(int reservationNumber) {
        int presentationId = 0;
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root");
            String queryPresentationId = "SELECT presentation_id FROM reservations WHERE reservation_id = " + reservationNumber;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryPresentationId);
            while (rs.next()) {
                presentationId = rs.getInt("presentation_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return presentationId;
    }
}
