package com.company;

public class Main {

    public static void main(String[] args) {

        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "X", "Y", "Z"};
        int[] counts = new int[26];
        String inputString = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.".toUpperCase();

        for (int i = 0; i < inputString.length(); i++) {                                    //Schleife inputString iterieren
            String inputLetter = inputString.substring(i, i + 1);
            getIndexOfLetter(alphabet, counts, inputLetter);
        }
        for (int i = 0; i < alphabet.length; i++) {                                         //Output
            System.out.println("Der Buchstabe " + alphabet[i] + " kommt " + counts[i] + " mal vor");
        }
    }

    public static void getIndexOfLetter(String[] alphabet, int[] counts, String inputLetter) {
        for (int j = 0; j < alphabet.length; j++) {                                    //Schleife alphabet iterieren
            String alphabetletter = alphabet[j];
            if (inputLetter.equals(alphabetletter)) {
                counts[j] = counts[j] + 1;
            }
        }
    }
}





