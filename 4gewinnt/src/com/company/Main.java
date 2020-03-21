package com.company;

import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {

        int height = 7;
        int width = 6;
        char playerA = 'X';
        char playerB = 'O';

        Scanner endScanner = new Scanner(System.in);

        while (true) {
            System.out.println("4 Gewinnt!");
            playGame(height, width, playerA, playerB);
            System.out.println("Möchtet ihr noch ein Spiel spielen? (Ja / Nein)");
            String end = endScanner.next();
            if (end.substring(0,1).toLowerCase().equals("n")) {
                break;
            }
        }
        System.out.println("Bis zum nächsten Mal!");
    }

    private static void playGame(int height, int width, char playerA, char playerB) {
        Scanner columnScanner = new Scanner (System.in);
        int stones = height * width;
        char playerMarking = ' ';
        char[][] fieldArray = new char[height][width];

        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                fieldArray[i][j] = playerMarking;
            }
        }

        //Feld ausgeben
        printField(fieldArray, playerA, playerB);

        //Spiel selbst
        char winner = '0';
        for (int i = 0; i < (stones + 1); i++) {
            if (i % 2 == 0) {
                playerMarking = playerA;
            } else {
                playerMarking = playerB;
            }

            //Schleife Stein Setzen (mit Check ob offenes Feld in Spalte)
            while (true) {

                System.out.println("Spieler " + playerMarking + ": In welche Spalte möchtest du deinen Stein fallen lassen?");
                int column = columnScanner.nextInt() - 1;

                boolean openField = checkOpenField(fieldArray, column);
                if (openField) {
                    setStone(fieldArray, playerMarking, column);
                    break;
                } else {
                    System.out.println("Diese Spalte ist bereits voll, bitte nochmals eingeben.");
                }
            }
            printField(fieldArray, playerA, playerB);
            winner = checkWin(playerMarking, fieldArray);
            if (winner == playerA || winner == playerB) {
                break;
            }
        }
        System.out.println();
        if (winner == playerA) {
            System.out.println("Spieler " + ANSI_RED + winner + ANSI_RESET + " gewinnt");
        } else if (winner == playerB) {
            System.out.println("Spieler " + ANSI_BLUE + winner + ANSI_RESET + " gewinnt");
        }
    }

    private static boolean checkOpenField(char[][] fieldArray, int column) {
        boolean openField = false;
        for (int j = fieldArray.length - 1; j > -1; j--) {
            if (fieldArray[j][column] == ' ') {
                openField = true;
                break;
            }
        }
        return openField;
    }

    private static char checkWin(char playerMarking, char[][] fieldArray) {
        char winner = '0';
        //Waagrecht
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length - 3; j++) {
                if (fieldArray[i][j] == playerMarking && fieldArray[i][j + 1] == playerMarking && fieldArray[i][j + 2] == playerMarking && fieldArray[i][j + 3] == playerMarking) {
                    winner = playerMarking;
                    break;
                }
            }
        }
        //Senkrecht
        for (int i = 0; i < fieldArray.length - 3; i++) {
            for (int j = 0; j < fieldArray[i].length ; j++) {
                if (fieldArray[i][j] == playerMarking && fieldArray[i + 1][j] == playerMarking && fieldArray[i + 2][j] == playerMarking && fieldArray[i + 3][j] == playerMarking) {
                    winner = playerMarking;
                    break;
                }
            }
        }
        //Diagonal absteigend
        for (int i = 0; i < fieldArray.length - 3; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == playerMarking && fieldArray[i + 1][j + 1] == playerMarking && fieldArray[i + 2][j + 2] == playerMarking && fieldArray[i + 3][j + 3] == playerMarking) {
                    winner = playerMarking;
                    break;
                }
            }
        }
        //Diagonal aufsteigend
        for (int i = fieldArray.length - 1; i > 2; i--) {
            for (int j = 0; j < fieldArray[i].length - 3; j++) {
                if (fieldArray[i][j] == playerMarking && fieldArray[i - 1][j + 1] == playerMarking && fieldArray[i - 2][j + 2] == playerMarking && fieldArray[i - 3][j + 3] == playerMarking) {
                    winner = playerMarking;
                    break;
                }
            }
        }
        return winner;
    }

    private static void setStone(char[][] fieldArray, char playerMarking, int column) {
        for (int j = fieldArray.length - 1; j > -1; j--) {
            if (fieldArray[j][column] == ' ') {
                fieldArray[j][column] = playerMarking;
                break;
            }
        }
    }

    private static void printField(char[][] fieldArray, char playerA, char playerB) {
        System.out.println();
        for (int i = 0; i < fieldArray.length; i++) {
            System.out.print((i + 1) + " |  ");
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == playerA) {
                    System.out.print(ANSI_RED + fieldArray[i][j] + ANSI_RESET + "  ");
                } else if (fieldArray[i][j] == playerB) {
                    System.out.print(ANSI_BLUE + fieldArray[i][j] + ANSI_RESET + "  ");
                } else
                    System.out.print(fieldArray[i][j] + "  ");
            }
            System.out.println();
        }
        String line = "   ";
        for (int i = 0; i < fieldArray[0].length; i++) {
            line = line + "---";
        }
        System.out.println(line);
        System.out.print("     ");
        for (int i = 0; i < fieldArray[0].length; i++) {
            System.out.print((i + 1) + "  ");
        }
        System.out.println();
    }
}
