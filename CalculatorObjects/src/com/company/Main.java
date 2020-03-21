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
                    Calculator.getArea(doubleScanner); break;
                case 2:
                    Calculator.getPerimeter(doubleScanner); break;
                case 3:
                    Calculator.getRoot(doubleScanner); break;
                default :
                    System.out.println("Keine Applikation erkannt. Bitte erneut eingeben:"); break;
            }
        }
    }
}
