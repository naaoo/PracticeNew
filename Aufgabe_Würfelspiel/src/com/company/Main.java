package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner stringScanner = new Scanner(System.in);
        int rounds = 6;
        int scorePlayer = 0;
        int scoreComputer = 0;
        // boolean playerstarts;
        int startCount = 0;         //0 = Spieler beginnt, 1 = PC beginnt
        int restart = 0;

        while (restart == 0) {

            //Start + Wer beginnt?
            System.out.println("Willkommen zum Würfelspiel!");
            System.out.println("Möchtest du beginnen? (Ja / Nein)");
            String starter = stringScanner.nextLine();
            if (starter.substring(0, 1).toUpperCase().equals("J")) {
                startCount = 0;
                System.out.println("Du beginnst!");
            } else if (starter.substring(0, 1).toUpperCase().equals("N")) {
                startCount = 1;
                System.out.println("Der PC beginnt:");
            } else {
                System.out.println("Error! Please restart");
                System.exit(0);
            }
            //Spiel selbst
            for (int i = startCount; i < ((rounds * 2) + startCount); i++) {
                if ((i % 2) == 0) {
                    int playerSingleThrow = playerPlaysRound(stringScanner);
                    scorePlayer = scorePlayer + playerSingleThrow;
                    System.out.println("\t Gesamtscore (DU) = " + scorePlayer);
                } else if ((i % 2) == 1) {
                    int pcSingleThrow = pcPlaysRound();
                    scoreComputer = scoreComputer + pcSingleThrow;
                    System.out.println("\t Gesamtscore (PC) = " + scoreComputer);
                }
            }
            //Verleich der Scores, Gewinner
            System.out.println();
            System.out.println("Gesamtscore DU = " + scorePlayer + " vs Gesamtscore PC = " + scoreComputer);
            if (scorePlayer > scoreComputer) {
                System.out.println("Du gewinnst!");
            } else if (scorePlayer < scoreComputer) {
                System.out.println("Der PC gewinnt!");
            } else {
                System.out.println("Unentschieden!");
            }
            //Revanche?
            System.out.println();
            System.out.println("Möchtest du nochmals spielen (Ja / Nein)?");
            String rematch = stringScanner.nextLine();
            if (rematch.substring(0, 1).toUpperCase().equals("N")) {
                restart = 1;
            }
        }


    }
        //Method Player spielt Runde
    public static int playerPlaysRound(Scanner stringScanner) {
            System.out.println("Bitte würfeln (Enter drücken)");
            String cont = stringScanner.nextLine();
            int SingleThrow = diceThrow();
            System.out.println("Du würfelst eine " + SingleThrow);
            return SingleThrow;
    }
        //Method PC spielt Runde
    public static int pcPlaysRound() {
            int SingleThrow = diceThrow();
            System.out.println("Computer würfelt eine " + SingleThrow);
            return SingleThrow;
    }
        // Method random throw
    public static int diceThrow() {
        Random randomNumber = new Random();
        int singleThrow = randomNumber.nextInt((6 - 1) + 1) + 1;
        return singleThrow;
    }
    /*
    // Spiel (Spieler beginnt)
    public static void playGamePlayerStarts(Scanner stringScanner, int rounds, int scorePlayer, int scoreComputer) {
        for (int i = 0; i < rounds; i++) {
            System.out.println("Runde " + (i + 1) + ":");
            System.out.println();
            scorePlayer = playerPlaysRound(stringScanner, scorePlayer);
            scoreComputer = pcPlaysRound(scoreComputer);
            System.out.println();
        }
    }
    // Spiel (Computer beginnt)
    public static void playGameComputerStarts(Scanner stringScanner, int rounds, int scorePlayer, int scoreComputer) {
        for (int i = 0; i < rounds; i++) {
            System.out.println("Runde " + (i + 1) + ":");
            System.out.println();
            scoreComputer = pcPlaysRound(scoreComputer);
            scorePlayer = playerPlaysRound(stringScanner, scorePlayer);
            System.out.println();
        }
    }
    */



}
