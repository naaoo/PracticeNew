package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int result = 0;
        int max = 30;
        int min = 10;

        while (true) {
            int x = generateRandomNumber(max, min);
            if (x == 15 || x == 25) {
                break;
            }
            else {
                result = result + x;
            }
        }
        System.out.println(result);
    }

    private static int generateRandomNumber(int max, int min) {
        Random random = new Random();
        int x = random.nextInt((max - min) + 1) + min;
        return x;
    }
}
/*
boolean not1525 = true;
while (not1525) {
        int x = generateRandomNumber(max, min);
        if (x == 15 || x == 25) {
        not1525 = false;
        }
        else {
        result = result + x;
        }
        }
        System.out.println(result);

 */