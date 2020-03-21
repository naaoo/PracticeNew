package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

     //Feld 3x3 erzeugen - done
     //Spieler A und B -done
     //Felder werden gefüllt in Durchgängen, bleiben
     //Siegbedingungen - Unentschieden

        String space = " ";
        String playerA = "X";
        String playerB = "O";
        Scanner playAgainScanner = new Scanner(System.in);

        while (true) {
            String[] marks = {" "," "," "," "," "," "," "," "," "};
            System.out.println("Willkommen zu TicTacToe!");
            System.out.println("Dies sind die Felder:");
            printEmptyField();

            //Feld printen
            //printField(marks);

            //Spiel
            int winner = 0;
            for (int i = 0; i < 9; i++) {
                winner = playRound(marks);
                if (winner > 0) {
                    break;
                }
            }
            switch (winner) {
                case 1:
                    System.out.println("Spieler A gewinnt!");
                    break;
                case 2:
                    System.out.println("Spieler B gewinnt!");
                    break;
                case 3:
                    System.out.println("Unentschieden!");
                    break;
            }
            System.out.println("Wollt ihr nochmals spielen? (Ja/Nein)");
            String playAgain = playAgainScanner.nextLine();
            if (playAgain.substring(0,1).toLowerCase().equals("n")) {
                break;
            }
        }







    }

    private static int checkWinConditions(String[] marks) {
        //Siegbedingungen
        int winner;
        if ((marks[0].equals("X") && marks[1].equals("X") && marks[2].equals("X"))
                || (marks[3].equals("X") && marks[4].equals("X") && marks[5].equals("X"))
                || (marks[6].equals("X") && marks[7].equals("X") && marks[8].equals("X"))
                || (marks[0].equals("X") && marks[3].equals("X") && marks[6].equals("X"))
                || (marks[1].equals("X") && marks[4].equals("X") && marks[7].equals("X"))
                || (marks[2].equals("X") && marks[5].equals("X") && marks[6].equals("X"))
                || (marks[0].equals("X") && marks[4].equals("X") && marks[8].equals("X"))
                || (marks[2].equals("X") && marks[4].equals("X") && marks[6].equals("X"))) {
            winner = 1;
        } else if ((marks[0].equals("O") && marks[1].equals("O") && marks[2].equals("O"))
                || (marks[3].equals("O") && marks[4].equals("O") && marks[5].equals("O"))
                || (marks[6].equals("O") && marks[7].equals("O") && marks[8].equals("O"))
                || (marks[0].equals("O") && marks[3].equals("O") && marks[6].equals("O"))
                || (marks[1].equals("O") && marks[4].equals("O") && marks[7].equals("O"))
                || (marks[2].equals("O") && marks[5].equals("O") && marks[6].equals("O"))
                || (marks[0].equals("O") && marks[4].equals("O") && marks[8].equals("O"))
                || (marks[2].equals("O") && marks[4].equals("O") && marks[6].equals("O"))) {
            winner = 2;
        } else if (Arrays.asList(marks).contains(" ")) {            //setzt winner auf 3 wenn noch leere Felder enthalten --> es geht weiter
            winner = 0;
        } else {
            winner = 3;                                             //Unentschieden
        }
        return winner;
    }

    private static int playRound(String[] marks) {
        Scanner boxScanner = new Scanner(System.in);
        int box;
        int winner = 0;
        System.out.println("Spieler A: setze dein X: (1-9)");
        box = boxScanner.nextInt();
        while (marks[box - 1].equals("X") || marks[box - 1].equals("O")) {
            System.out.println("Feld bereits besetzt");
            System.out.println("Spieler A: setze dein X nochmals: (1-9)");
            box = boxScanner.nextInt();
        }
        marks[box - 1] = "X";
        printField(marks);
        winner = checkWinConditions(marks);
        if (winner > 0) {
            return winner;                  //möglicherweise Problem...?
        }

        System.out.println("Spieler B: setze dein O: (1-9)");
        box = boxScanner.nextInt();
        while (marks[box - 1].equals("X") || marks[box - 1].equals("O")) {
            System.out.println("Feld bereits besetzt");
            System.out.println("Spieler B: setze dein O nochmals: (1-9)");
            box = boxScanner.nextInt();
        }
        marks[box - 1] = "O";
        printField(marks);
        winner = checkWinConditions(marks);
        return winner;
    }

    private static void printEmptyField() {
        System.out.println("-------------------------");
        for (int i = 0; i < 9; i++) {
            System.out.print("|\t" + (i + 1) + "\t");
            if (i == 2 || i == 5 || i == 8) {
                System.out.println("|");
                System.out.println("-------------------------");
            }

        }
    }

    private static void printField(String[] marks) {
        System.out.println("-------------------------");
        for (int i = 0; i < 9; i++) {
            System.out.print("|\t" + marks[i] + "\t");
            if (i == 2 || i == 5 || i == 8) {
                System.out.println("|");
                System.out.println("-------------------------");
            }

        }
    }
}
