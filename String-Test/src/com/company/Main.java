package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// Experimente
/*
        String testString = "Dies ist der Teststring";
        String compare = "Dies ist der 2. Teststring";

        System.out.println("Vergleich ist korrekt: " + testString.equalsIgnoreCase(compare));

        System.out.println("Einzelner Character: " + testString.charAt(13));

        System.out.println("Substring: " + testString.substring(0,5));

        System.out.println("BLOCK: " + testString.toUpperCase());

        System.out.println("klein: " + testString.toLowerCase());

        System.out.println("String endet mit g: " + testString.endsWith("g"));

        System.out.println("i replaced durch 7: " + testString.replace("i","7"));
        System.out.println();
*/
    //Substring

        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);

        System.out.println("Welchen String möchtest du substringen?");
        String fullString = stringScanner.nextLine();

        System.out.println("An welcher Stelle möchtest du beginnen (Erste Stelle = 1)?");
        int start = (intScanner.nextInt() - 1);

        System.out.println("Möchtest du die Länge des Substrings (press 1) oder den letzten Character (press 2) angeben?");
        int methode = intScanner.nextInt();

        if (methode == 1) {
            System.out.println("Wie lange soll der Substring sein?");
            int length = intScanner.nextInt();
            String subString1 = methodSubstringLength(fullString, start, length);
        }
        else if (methode == 2) {
            System.out.println("Welche soll die letzte Stelle des Substrings sein?");
            int end = (intScanner.nextInt());
            String subString = methodSubstring(fullString, start, end);
        }


    }
    public static String methodSubstring(String startString, int first, int last) {
        String subString = "";
        for (int i = first; i < last; i++ ) {
            subString = subString + startString.charAt(i);
        }
        System.out.println("Der Substring ist: " + subString);
        return subString;
    }
    public static String methodSubstringLength(String startString, int first, int length) {
        String subString = "";
        for (int i = first; i < (first + length); i++ ) {
            subString = subString + startString.charAt(i);
        }
        System.out.println("Der Substring ist: " + subString);
        return subString;
    }

}
