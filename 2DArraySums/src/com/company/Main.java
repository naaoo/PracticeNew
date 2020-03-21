package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int vert = 7;
        int horiz = 7;

        int[][] randomArray = new int[vert][horiz];
        fillArrayRandomly(randomArray);

        // Print randomArray
        PrintRandomArray(randomArray);

        // Horizontal addition
        int[] sumHorizArr = horizontalAddition(randomArray, vert);
        //Print sumHoriz
        System.out.println("Sums horizontal:");
        Arrays.sort(sumHorizArr);
        printSum(sumHorizArr);

        // Vertical addition
        int[] sumVertArr = verticalAddition(horiz, randomArray);

        // Print sumVert
        System.out.println("Sums vertical:");
        Arrays.sort(sumVertArr);
        printSum(sumVertArr);

        // Diagonal sums
        int sumD1 = getSumD1(randomArray);
        System.out.println("Diagonal 1: " + sumD1);

        int sumD2 = getSumD2(randomArray);
        System.out.println("Diagonal 2: " + sumD2);

        //compare D1 & D2
        compareSumDs(sumD1, sumD2);
    }





    private static void compareSumDs(int sumD1, int sumD2) {
        if (sumD1 > sumD2) {
            System.out.println("Sum of D1 is higher");
        } else if (sumD2 > sumD1) {
            System.out.println("Sum of D2 is higher");
        } else {
            System.out.println("The diagonals have the same sum");
        }
    }

    private static int getSumD2(int[][] randomArray) {
        int sumD2 = 0;
        for (int i = 0; i < randomArray.length; i++) {
            sumD2 = sumD2 + randomArray[i][randomArray[i].length - 1 - i];
        }
        return sumD2;
    }

    private static int getSumD1(int[][] randomArray) {
        int sumD1 = 0;
        for (int i = 0; i < randomArray.length; i++) {
            sumD1 = sumD1 + randomArray[i][i];
        }
        return sumD1;
    }

    private static void printSum(int[] sumArr) {
        for (int i = 0; i < sumArr.length; i++) {
            System.out.println(sumArr[i]);
        }
        System.out.println();
    }

    private static int[] verticalAddition(int horiz, int[][] randomArray) {
        int[] sumVertArr = new int[randomArray[horiz - 1].length];
        for (int i = 0; i < sumVertArr.length; i++) {
            int sum = 0;
            for (int j = 0; j < horiz; j++) {
                    sum = sum + randomArray[j][i];
            }

            sumVertArr[i] = sum;
        }
        return sumVertArr;
    }

    private static int[] horizontalAddition(int[][] randomArray, int vert) {
        int[] sumHorizArr = new int[randomArray[vert - 1].length];
        for (int i = 0; i < randomArray.length; i++) {
            int sum = 0;
            for (int j = 0; j < randomArray[i].length; j++) {
                sum = sum + randomArray[i][j];
            }
            sumHorizArr[i] = sum;
        }
        return sumHorizArr;
    }

    private static void PrintRandomArray(int[][] randomArray) {
        for (int i = 0; i < randomArray.length; i++) {
            for (int j = 0; j < randomArray[i].length; j++) {
                System.out.print(randomArray[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void fillArrayRandomly(int[][] randomArray) {
        Random random = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            for (int j = 0; j < randomArray[i].length; j++) {
                randomArray[i][j] = random.nextInt(29) + 1;
            }
        }
    }
}
