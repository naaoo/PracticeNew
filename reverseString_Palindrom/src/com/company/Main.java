package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Please enter a word below:");
        String rawString = stringScanner.nextLine();
        String stringLowerCase = rawString.toLowerCase();

        String reverseString = reverseString(stringLowerCase);
        System.out.println("Reversed string: " + reverseString);

        boolean isPalindrome = checkIfPalindrome(stringLowerCase, reverseString);
        if (isPalindrome) {
            System.out.println(rawString + " is a palindrome");
        } else {
            System.out.println(rawString + " is not a palindrome");
        }
    }
    private static String reverseString(String stringLowerCase) {
        String reverseString = "";

        for (int i = stringLowerCase.length() -1; i > -1; i--) {
            reverseString = reverseString + stringLowerCase.charAt(i);
        }
        return reverseString;
    }
    private static boolean checkIfPalindrome(String stringLowerCase, String reverseString) {
        boolean isPalindrome;
        if (reverseString.equals(stringLowerCase)) {
            isPalindrome = true;
        } else {
            isPalindrome = false;
        }
        return isPalindrome;
    }
}
