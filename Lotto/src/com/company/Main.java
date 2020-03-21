package com.company;

import java.util.Scanner;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int rounds = 7;
        int range = 49;
        int[] numbers = new int[rounds];

        scanNumbers(rounds, numbers);
        printTicket(numbers, range);

    }
    //Methode Zahlen scannen
    public static int[] scanNumbers(int rounds, int[] numbers) {
        Scanner intScanner = new Scanner(System.in);

        for (int i = 0; i < rounds; i++) {
            System.out.println("Bitte gib eine Zahl zwischen 1 und 49 an. (" + (rounds - i) + " Zahlen fehlen noch)");
            numbers[i] = intScanner.nextInt();
        }
        return numbers;
    }

    //Methode Wert i in Array enthalten
    public static boolean contains(int i, int[] numbers) {
       // return Arrays.binarySearch(numbers, i) >= 0;
        boolean contained = false;
        for (int j = 0; j < numbers.length; j++) {
            if(i == numbers[j]) {
                contained = true; break;
            }
        }
        return contained;
    }

    //Methode Print Ticket
    public static void printTicket(int[] numbers, int range) {
        String emptyField = ("|_|");
        String filledField = ("|X|");
        for (int i = 1; i < (range + 1); i++) {
            if (contains(i, numbers)) {
                System.out.print(filledField);
            }
            else {
                System.out.print(emptyField);
            }
            if (i % 7 == 0) {
                System.out.println();
            }
        }
    }
}

