package com.company;

import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {

        //Test number Var1
        double number = 120.0;
        System.out.println("Var1:");

        if(number <= Byte.MAX_VALUE && number >= Byte.MIN_VALUE ) {
            System.out.println("the number " + number + " is within byte range");
        }
        else if (number <= Short.MAX_VALUE && number >= Short.MIN_VALUE ) {
            System.out.println("the number " + number + " is within short range");
        }
        else if (number <= Integer.MAX_VALUE && number >= Integer.MIN_VALUE ) {
            System.out.println("the number " + number + " is within int range");
        }
        else if (number <= Long.MAX_VALUE && number >= Long.MIN_VALUE ) {
            System.out.println("the number " + number + " is within long range");
        }
        else {
            System.out.println("the number " + number + " is not in any classes range");
        }



        //Test number Var2
        System.out.println("Var2:");
        if (number <= Long.MAX_VALUE && number > Integer.MAX_VALUE) {
            System.out.println("the number " + number + " is within long range but not in any other range");
        }
        else if (number <= Integer.MAX_VALUE && number > Short.MAX_VALUE) {
            System.out.println("the number " + number + " is within long and int range");
        }
        else if (number <= Short.MAX_VALUE && number > Byte.MAX_VALUE) {
            System.out.println("the number " + number + " is within long, int and short range");
        }
        else if (number <= Byte.MAX_VALUE) {
            System.out.println("the number " + number + " is within long, int, short and byte range");
        }
        else {
            System.out.println("the number " + number + " is not in any classes range");
        }


        //Test number Var3
        System.out.println("Var3:");
        boolean maxLong = (number <= Long.MAX_VALUE && number > Integer.MAX_VALUE);
        boolean maxInt = (number <= Integer.MAX_VALUE && number > Short.MAX_VALUE);
        boolean maxShort = (number <= Short.MAX_VALUE && number > Byte.MAX_VALUE);
        boolean maxByte = (number <= Byte.MAX_VALUE);

        if (maxLong){
            System.out.println("the number " + number + " is within long range but not in any other range");
        }
        else if (maxInt){
            System.out.println("the number " + number + " is within long and int range");
        }
        else if (maxShort){
            System.out.println("the number " + number + " is within long, int and short range");
        }
        else if (maxByte){
            System.out.println("the number " + number + " is within long, int, short and byte range");
        }
        else if (!maxByte && !maxShort && !maxInt && !maxLong){
            System.out.println("the number " + number + " is not in any classes range");
        }


        //Test Bedienungsoperator
        String output = number > 10 ? "the number is higher than 10" : "the number is 10 or lower";
        System.out.println(output);


        //1. Grade Var1
        int grade = 4;

        switch (grade){
            case 1: System.out.println("Note: sehr gut"); break;
            case 2: System.out.println("Note: gut"); break;
            case 3: System.out.println("Note: befriedigend"); break;
            case 4: System.out.println("Note: genügend"); break;
            case 5: System.out.println("Note: nicht genügend"); break;
            default: System.out.println("Dies ist keine bekannte Schulnote"); break;
        }


        //1. Grade Var2
        if (grade == 1){
            System.out.println("Note: Sehr gut");
        }
        else if (grade == 2) {
            System.out.println("Note: gut");
        }
        else if (grade == 3) {
            System.out.println("Note: befriedigend");
        }
        else if (grade == 4) {
            System.out.println("Note: genügend");
        }
        else if (grade == 5) {
            System.out.println("Note: nicht genügend");
        }
        else{
            System.out.println("Keine bekannte Note");
        }


        //1.1 Grade reverse
        String gradeText = "GenÜgend";
        String gradeLow = gradeText.toLowerCase();
        switch (gradeLow){
            case ("sehr gut"): System.out.println(1); break;
            case ("gut"): System.out.println(2); break;
            case ("befriedigend"): System.out.println(3); break;
            case ("genügend"): System.out.println(4); break;
            case ("nicht genügend"): System.out.println(5); break;
            default: System.out.println("Dies ist keine bekannte Schulnote"); break;
        }


        //Bowling Pins
        int pins = 5;
        switch (pins){
            case 0: System.out.println("Verworfen!"); break;
            case 1: case 2: case 3: System.out.println("okay"); break;
            case 4: case 5: case 6: System.out.println("guter Wurf"); break;
            case 7: case 8: System.out.println("sehr guter Wurf"); break;
            case 9: System.out.println("Perfekt!");
            default: System.out.println("Diese Pinanzahl existiert nicht");
        }

    }
}
