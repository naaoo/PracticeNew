package com.company;


public class Main {

    public static void main(String[] args) {

        int[] inputArray = {3, -2, 9, 18, 22, 0, 9, 1223322};
        int[] inputArrayMan = inputArray;
        int[] sortedArray = new int[inputArray.length];
        int lowestInt;
        int removeAtIndex;

        for (int i = 0; i < inputArrayMan.length; i++) {
            lowestInt = Integer.MAX_VALUE;
            removeAtIndex = i;
            for (int j = 0; j < inputArrayMan.length; j++) {
                if (lowestInt > inputArrayMan[j]) {
                    lowestInt = inputArrayMan[j];
                    removeAtIndex = j;
                }
            }
            sortedArray[i] = lowestInt;

            //"REmove" Object (= set to intMAX)
            for (int j = 0; j < inputArrayMan.length; j++) {
                if (removeAtIndex != j) {
                    inputArrayMan[j] = inputArrayMan[j];
                } else {
                    inputArrayMan[j] = Integer.MAX_VALUE;
                }
            }
        }
        System.out.print("Sorted Array: ");
        for (int i = 0; i < inputArrayMan.length; i++) {
            if (i == (inputArrayMan.length - 1)) {
                System.out.println(sortedArray[i]);
            } else {
                System.out.print(sortedArray[i] + ",");
            }
        }
    }
}
