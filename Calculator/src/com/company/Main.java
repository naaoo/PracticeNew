package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner applicationScanner = new Scanner(System.in);
        Scanner calculatorScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);

        System.out.println("Welche Applikation möchtest du ausführen?");
        System.out.println("Für Taschenrechner drücke 1, für Währungsrechner drücke 2");
        int application = applicationScanner.nextInt();

        if (application == 1) {                                                     //Taschenrechner
            System.out.println("Bitte gib deine erste Zahl ein:");
            double num1 = calculatorScanner.nextDouble();

            System.out.println("Welche Operation möchtest du durchführen?");
            String operator = stringScanner.nextLine();

            System.out.println("Bitte gib deine zweite Zahl ein:");
            double num2 = calculatorScanner.nextDouble();

            double result = calculator(num1, num2, operator);
            System.out.println(result);

            /*
            if (operator.equals("+")) {
                double resAdd = addition(num1, num2);
                System.out.println("Result: " + resAdd);
            } else if (operator.equals("-")) {
                double resSub = subtraction(num1, num2);
                System.out.println(resSub);
            } else if (operator.equals("*")) {
                double resMult = multiplication(num1, num2);
                System.out.println(resMult);
            } else if (operator.equals("/")) {
                double resDiv = division(num1, num2);
                System.out.println(resDiv);
            }
             */
        }
        else if (application == 2) {                                                        //Währungsrechner
            System.out.println("In welche $ möchtest du umrechnen?");
            System.out.println("Möglich sind: USD, CAD, AUD");
            String currency = stringScanner.nextLine();
            System.out.println("Welchen EUR-Betrag möchtest du umrechnen?");
            double value = calculatorScanner.nextDouble();

            double valueForeign = calculateCurrency(value, currency);
            System.out.println(value + "EUR entspricht " + valueForeign + currency);
        }
    }

    public static double calculator(double num1, double num2, String operator) {              //Calculator
        double result = 0;
        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            result = num1 / num2;
        } return result;
    }

    /*
    public static double addition(double num1, double num2) {                               //Einzelne Operationen
        return num1 + num2;
    }
    public static double subtraction(double num1, double num2) {
        return num1 - num2;
    }
    public static double multiplication(double num1, double num2) {
        return num1 * num2;
    }
    public static double division(double num1, double num2) {
        return num1 / num2;
    }
     */

    public static double calculateCurrency(double value, String currency) {                 //calculateCurrency
        double factor = 1;
        if (currency.equals("USD")) {
            factor = 1.08;
        } else if (currency.equals("CAD")) {
            factor = 1.44;
        } else if (currency.equals("AUD")) {
            factor = 1.62;
        }
        double valueForeign = value * factor;
        return valueForeign;
    }
}








