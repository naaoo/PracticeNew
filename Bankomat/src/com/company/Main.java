package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int end = 1;
        int balance = 300000;
        int amount;
        Scanner intScanner = new Scanner(System.in);

        System.out.print("Welche Funktion möchtest du ausführen?:\n\t1: Einzahlen\n\t2: Abheben\n\t3: Kontostand anzeigen\n\t4: Beenden\n");
        while (end == 1) {
            int function = intScanner.nextInt();
            if (function == 1) {
                balance = addMoney(balance, intScanner);
            }
            else if (function == 2) {
                balance = subMoney(balance, intScanner);
            }
            else if (function == 3) {
                displayBalance(balance);
            }
            else if (function == 4) {
                end = endProgam();
            }
            else {
                System.out.println("Dies ist keine ausführbare Funktion. Bitte nochmals eingeben.");
            }
        }
    }

    private static int endProgam() {
        int end;
        System.out.println("Bankomat schaltet sich ab...");
        end = 0;
        return end;
    }

    private static void displayBalance(int balance) {
        System.out.println("Dein Kontostand beträgt: € " + balance);
    }

    private static int subMoney(int balance, Scanner intScanner) {
        int amount;
        System.out.println("Welchen Betrag möchtest du abheben?");
        amount = intScanner.nextInt();
        balance = balance - amount;
        System.out.println("Neuer Kontostand: € " + balance);
        return balance;
    }

    private static int addMoney(int balance, Scanner intScanner) {
        int amount;
        System.out.println("Welchen Betrag möchtest du einzahlen?");
        amount = intScanner.nextInt();
        balance = balance + amount;
        System.out.println("Neuer Kontostand: € " + balance);
        return balance;
    }
}
