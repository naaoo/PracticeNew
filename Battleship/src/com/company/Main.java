package com.company;


import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {

        // field a*b
        int a = 10;
        int b = 10;
        char[][] field = new char[a][b];                      //shots from player are saved here (displayed)
        Scanner positionScanner = new Scanner(System.in);     //processes Strings (Position and direction)

        // fill field
        fillField(field);                                     //field is set empty at start

        // player/pc field
        char[][] playerField = field;                         //player sets his ships here (displayed)
        char[][] pcField = field;                             //computer sets his ships here (not displayed)

        // print field
        printField(field);                                    //same method used for player/pc/field

        //Player vs PC (random)
        char ship = 'X';    //no color
        char shot = 'o';    //blue
        char hit = 'O';     //red

        //10 ships (horiz/vert) - 1 battleship (5), 2 cruiser (4), 3 destroyer (3), 4 submarine (2)
        int amountBattleship = 1;
        int lengthBattleship = 5;
        int amountCruiser = 2;
        int lengthCruiser = 4;
        int amountDestroyer = 3;
        int lengthDestroyer = 3;
        int amountSubmarine = 4;
        int lengthSubmarine = 2;

        // get position
        System.out.print("Please name a position (e.g. A/1): ");
        String position = getPosition(positionScanner);

        int row = Integer.parseInt(position.substring(2)) - 1;
        int column = Character.getNumericValue(position.charAt(0)) - 10;

        // get direction
        System.out.print("Do you want to place your ship vertically or horizontally (v/h)");
        char direction = positionScanner.nextLine().toLowerCase().charAt(0);

        // place ship
        if (direction == 'v') {

        }









        //abwechselnd Schießen ("getroffen/versenkt" oder "Wasser"
        //Treffer/Fehlschüsse müssen angezeigt werden (ev mit Farbe arbeiten?)

        //Check ob noch nicht getroffene Schiffsteile

        //Spiel startet wieder bis Abbruch --> Highscore (minimale Runden) wird gespeichert

        //Modus bei Treffer weiterspielen (mit Auswahl welcher Modus gewünscht)





        // 2 Arrays (mult) gesetzte Schiffe Player / Computer


        // Schiffe setzen (mit Fragen: Startfeld, waagrecht/senkrecht)
        // werden in Array gespeichert






    }

    private static String getPosition(Scanner positionScanner) {
        return positionScanner.nextLine();
    }

    private static void printField(char[][] field) {
        System.out.println("\t\t\tA\t|\tB\t|\tC\t|\tD\t|\tE\t|\tF\t|\tG\t|\tH\t|\tI\t|\tJ\t|");
        for (int i = 0; i < field.length; i++) {
            System.out.print("|\t" + (i + 1) + "\t");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("|\t" + field[i][j] + "\t");
            }
            System.out.println("|");
        }
    }

    private static void fillField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '-';
            }
        }
    }
}
