package com.company;

public class Main {

    public static void main(String[] args) {



        //Digit sum = 15
        for (int i = 1; i < 1001; i++) {
            int digitSum = getDigitSum(i);
            if (digitSum == 15) {
                System.out.println("Digit sum of " + i + " = 15");
            }
        }

        //Digit sum = divisable by 7
        for (int i = 1; i < 1001; i++) {
            int digitSum = getDigitSum(i);
            if (digitSum % 7 == 0) {
                System.out.println("Digit sum of " + i + " is divisible by 7");
            }
        }

        //Most common digit sum
            //getDigitSumArray
        int[] allDigitSums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            int digitSum = getDigitSum(i);
            allDigitSums[i] = digitSum;
        }
            //count digit sums
        int[] counts = new int[allDigitSums.length];
        for (int i = 0; i < allDigitSums.length; i++) {
            int intAtPosI = allDigitSums[i];
            for (int j = 0; j < allDigitSums.length; j++) {
                int intAtPosJ = allDigitSums[j];
                if (intAtPosI == intAtPosJ) {
                    counts[j] = counts[j] + 1;
                }
            }
        }
            //most common
        int highestCount = 0;
        int mostCommonDigitSum = 0;
        for (int i = 0; i < allDigitSums.length; i++) {
            if (counts[i] > highestCount) {
                highestCount = counts[i];
                mostCommonDigitSum = allDigitSums[i];
            }

        }
        System.out.println("Most common digit sum = " + mostCommonDigitSum + " (" + highestCount + " times)");

        //Digit Root
        int x = 47;
        int digitSum = getDigitSum(x);
        int digitRoot = getDigitSum(digitSum);
        System.out.println("The digit root of " + x + " is " + digitRoot);
    }

    private static int getDigitSum(int x) {
        int digitSum = 0;
        String xString = Integer.toString(x);
        char[] xChar = xString.toCharArray();
        for (int i = 0; i < xChar.length; i++) {
            digitSum = digitSum + Character.getNumericValue(xChar[i]);
        }
        return digitSum;
    }
}


