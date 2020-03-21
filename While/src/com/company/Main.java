package com.company;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {

        //Aufgabe 1.1
        int h = 5;
        int j = 1;
        while (j < 11) {
            System.out.println(h + " * " + j + " = " + (5*j));
            j++;
        }

        //Aufgabe 1.2
        for (int k = 1; k < 11; k++) {
            System.out.println(h + " * " + k + " = " + (5*k));
        }


        //Aufgabe 2
        int breite = 7;
        int höhe = 5;

        for (int k = 0; k < höhe; k++) {
            for (int l = 0; l < breite; l++) {
                System.out.print("X");
            }
            System.out.println("");
        }


        //Pyramide
        int i = 1;
        String a = "*";
        while (i <= 5) {
            i++;
            System.out.println(a);
            a = a + "*";
        }
        String b = "*****";
        while (i > 1) {
            --i;
            System.out.println(b = b.substring(0, b.length() - 1));
        }


        //Krone
        for (int y = 1; y < 4; y++) {
            for (int x = 1; x < 10; x++) {
                if (y == 1) {
                    if (x == 1 || x == 5 || x == 9) {
                        System.out.print("X");
                    }
                    else {
                        System.out.print(" ");
                    }
                }
                else if (y == 2) {
                    if (x == 3 || x == 7) {
                        System.out.print(" ");
                    }
                    else {
                        System.out.print("X");
                    }
                }
                else if (y == 3) {
                    System.out.print("X");
                }
            }
            System.out.println("");

        }
    }


}