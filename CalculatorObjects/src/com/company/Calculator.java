package com.company;

import java.util.Scanner;

public class Calculator {

    public static double getRoot(Scanner doubleScanner) {
        System.out.println("Wurzelberechnung wurde gestartet");
        System.out.println("Aus welcher Zahl möchtest du die Wurzel ziehen?");
        double num = doubleScanner.nextDouble();
        System.out.println("Die Wurzel aus " + num + " beträgt: " + Math.sqrt(num));
        return Math.sqrt(num);
    }

    public static void getPerimeter(Scanner doubleScanner) {
        System.out.println("Umfangsberechnung wurde gestartet");
        System.out.println("Bitte gib den Radius des Kreises ein:");
        double radius = doubleScanner.nextDouble();
        System.out.println("Der Umfang beträgt: " + (Math.pow(radius, 2.0)*Math.PI));
    }

    public static void getArea(Scanner doubleScanner) {
        System.out.println("Flächenberechnung wurde gestartet");
        System.out.println("Gib die erste Seite des Rechtecks ein:");
        double a = doubleScanner.nextDouble();
        System.out.println("Gib die zweite Seite ein:");
        double b = doubleScanner.nextDouble();
        System.out.println("Fläche: " + (a*b));
    }
}
