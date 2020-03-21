package com.company;

public class Main {

    public static void main(String[] args) {

        //Kalender

        String month = "Mai";
        int startDay = 5;
        int daysInMonth = 0;

        switch (month) {
            case "Januar":
                daysInMonth = 31;
                break;
            case "Jänner":
                daysInMonth = 31;
                break;
            case "Februar":
                daysInMonth = 28;
                break;
            case "März":
                daysInMonth = 31;
                break;
            case "April":
                daysInMonth = 30;
                break;
            case "Mai":
                daysInMonth = 31;
                break;
            case "Juni":
                daysInMonth = 30;
                break;
            case "Juli":
                daysInMonth = 31;
                break;
            case "August":
                daysInMonth = 31;
                break;
            case "September":
                daysInMonth = 30;
                break;
            case "Oktober":
                daysInMonth = 31;
                break;
            case "November":
                daysInMonth = 30;
                break;
            case "Dezember":
                daysInMonth = 31;
                break;
            default:
                System.out.println("Bitte schreibe den Monat korrekt...");
                break;
        }

        int day = 1;
        int lines = 6;
        if ((startDay + daysInMonth) > 35){
            lines = lines +1;
        }

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

    //Aufgabe Array:
        System.out.println("Aufgabe Array:");

        int[] persons = {80,100,120,500,600,200};
        int sum =0;
        int i = 0;

        while (i < persons.length){
            sum = sum + persons[i];
            i++;
            if (sum > 1600){
                break;
            }
        }
        System.out.println("Summe: " + sum);
        if (sum > 1600){
            System.out.println("Zulässiges Gewicht überschritten!");
        } else {
            System.out.println("Alles in Ordnung!");
        }





    }
}

