package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Kalender Methoden


        printHeader();
        printEmptyCalendar();
        printCalendar();



        /*
        System.out.println("Monat: " + month);
        System.out.println("|\tMO\t|\tDI\t|\tMI\t|\tDO\t|\tFR\t|\tSA\t|\tSO\t|");
        for (int y = 1; y < lines; y++) {
            for (int x = 0; x < 7; x++) {
                if (y == 1 && x < startDay){
                    System.out.print("|\t" + " " + "\t");
                }
                else if (day > daysInMonth){
                    System.out.print("|\t" + " " + "\t");
                }
                else {
                    if (x > 4){
                        System.out.print("|\t" + "WE" + "\t");
                        day++; continue;
                    }
                    System.out.print("|\t" + day + "\t");
                    day++;
                }
            }
            System.out.println("|");
        }
        System.out.println("");
        */

    }
    //Methode Kalenderkopf (printHeader)
    public static void printHeader() {
            String header = "|\tMO\t|\tDI\t|\tMI\t|\tDO\t|\tFR\t|\tSA\t|\tSO\t|";
            System.out.println("Monat: " );
            System.out.println(header);
    }
    //Methode leerer Kalender (printEmptyCalendar)
    public static void printEmptyCalendar() {
        String space = " ";
        String field = "|\t" + space + "\t";
        for (int y = 1; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                System.out.print(field);
            }
            System.out.println("|");
        }
        System.out.println("");
    }
    //Methode gefüllter Kalender (printCalendar)
   public static void printCalendar() {

        Scanner monthScanner = new Scanner(System.in);
        Scanner startDayScanner = new Scanner(System.in);

        System.out.println("Welchen Monat möchtest du anzeigen? (Bitte korrekt schreiben)");
        String month = monthScanner.nextLine();
        System.out.println("An welchem Tag der Woche startet der Monat? (MO = 0 usw.)");
        int startDay = startDayScanner.nextInt();
        int daysInMonth = 0;

       String space = " ";
       String field = "|\t" + space + "\t";
       String filledField = "|\t ";
       String filledWE = "|\t" + "WE" + "\t";

       switch (month) {
           case "Januar": daysInMonth = 31; break;
           case "Jänner": daysInMonth = 31; break;
           case "Februar": daysInMonth = 28; break;
           case "März": daysInMonth = 31; break;
           case "April": daysInMonth = 30; break;
           case "Mai": daysInMonth = 31; break;
           case "Juni": daysInMonth = 30; break;
           case "Juli": daysInMonth = 31; break;
           case "August": daysInMonth = 31; break;
           case "September": daysInMonth = 30; break;
           case "Oktober": daysInMonth = 31; break;
           case "November": daysInMonth = 30; break;
           case "Dezember": daysInMonth = 31; break;
           default: System.out.println("Bitte schreibe den Monat korrekt..."); break;
       }
       int day = 1;
       int lines = 6;
       if ((startDay + daysInMonth) > 35){
           lines = lines +1;
       }
       printHeader();
        for (int y = 1; y < lines; y++) {
            for (int x = 0; x < 7; x++) {
                if (y == 1 && x < startDay){
                    System.out.print(field);
                }
                else if (day > daysInMonth){
                    System.out.print(field);
                }
                else {
                    if (x > 4){
                        System.out.print(filledWE);
                        day++; continue;
                    }
                    String fullFilledField = filledField + day + "\t";
                    System.out.print(fullFilledField);
                    day++;
                }
            }
            System.out.println("|");
        }
        System.out.println("");
    }
}


