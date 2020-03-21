package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner intScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        int app = 0;

        while (true) {
            System.out.println("Welche Applikation möchtest du ausführen?:\n\t1: Rechteck - Fläche\n\t2: Kreis - Umfang\n\t3: Wurzelberechnung");
            app = intScanner.nextInt();
            switch (app) {
                case 1:
                    getArea(doubleScanner); break;
                case 2:
                    getPerimeter(doubleScanner); break;
                case 3:
                    getRoot(doubleScanner); break;
                default :
                    System.out.println("Keine Applikation erkannt. Bitte erneut eingeben:"); break;
            }
        }
    }

    private static void getRoot(Scanner doubleScanner) {
        System.out.println("Wurzelberechnung wurde gestartet");
        System.out.println("Aus welcher Zahl möchtest du die Wurzel ziehen?");
        double num = doubleScanner.nextDouble();
        System.out.println("Die Wurzel aus " + num + " beträgt: " + Math.sqrt(num));
        return;
    }

    private static void getPerimeter(Scanner doubleScanner) {
        System.out.println("Umfangsberechnung wurde gestartet");
        System.out.println("Bitte gib den Radius des Kreises ein:");
        double radius = doubleScanner.nextDouble();
        System.out.println("Der Umfang beträgt: " + (Math.pow(radius, 2.0)*Math.PI));
    }

    private static void getArea(Scanner doubleScanner) {
        System.out.println("Flächenberechnung wurde gestartet");
        System.out.println("Gib die erste Seite des Rechtecks ein:");
        double a = doubleScanner.nextDouble();
        System.out.println("Gib die zweite Seite ein:");
        double b = doubleScanner.nextDouble();
        System.out.println("Fläche: " + (a*b));
    }
}
