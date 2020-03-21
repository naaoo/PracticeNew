package com.company;

public class Main {

    public static void main(String[] args) {

        //A1. Aufgabe Boolsche Ausdrücke
        //A1.1
        int age = 23;
        boolean hasLicense = true;
        boolean isTest = false;
        boolean allowed = (age >= 24 || (hasLicense && !isTest));
        System.out.println("A1.1: Motorradfahren erlaubt : " + allowed);

        //A1.2
        int ageKid = 13;
        boolean hasParent = true;
        boolean allCinema = (ageKid > 14 || hasParent);
        System.out.println("A1.2: Person darf den Film sehen: " + allCinema);

        //A1.3
        int exp = 1;
        boolean hasCc = false;
        boolean allArchitecture = (exp >= 2 || hasCc);
        System.out.println("A1.3: Person darf Kurs besuchen: " + allArchitecture);

        //A1.4
        int exp2 = 3;
        boolean hasMSc = false;
        boolean isCriminal = true;
        boolean hasChance = ((exp2 >= 5 || hasMSc) && !isCriminal);
        System.out.println("A1.4: Person hat Chance auf Anstellung: " + hasChance);

        //A2: Aufgabe Bedienungsanweisungen
        //A2.1
        String outputA2_1 = (age >= 24 || (hasLicense && !isTest)) ? "darf Motorradfahren" : "darf nicht Motorradfahren";
        System.out.println("A2.1: " + outputA2_1);
        //A2.2
        String outputA2_2 = (ageKid > 14 || hasParent) ? "darf Film sehen" : "darf Film nicht sehen";
        System.out.println("A2.2: " + outputA2_2);
        //A2.3
        String outputA2_3 = (exp >= 2 || hasCc) ? "darf Kurs besuchen" : "darf Kurs nicht besuchen";
        System.out.println("A2.3: " + outputA2_3);
        //A2.4
        String outputA2_4 = ((exp2 >= 5 || hasMSc) && !isCriminal) ? "hat Chance" : "hat keine Chance";
        System.out.println("A2.4: " + outputA2_4);

        //A3: Aufgabe Schleifen
        //A3.1
        System.out.println("");
        System.out.println("A3.1:");
        System.out.println("");

        for (int i = 0; i < 4; i++) {
            if (i % 2 == 1) {
                System.out.println("0000");
            } else {
                System.out.println("XXXX");
            }
        }

        //A3.2.1
        System.out.println("");
        System.out.println("A3.2.1:");

        int a = 0;
        for (int i = 0; i < 101; i++) {
            if (i % 2 == 0) {
                a = a + i;
            }
        }
        System.out.println("Summe aller geraden Zahlen: " + a);

        //A3.2.2
        System.out.println("");
        System.out.println("A3.2.2:");

        int b = 0;
        for (int i = 0; i < 101; i = i + 2) {
            b = b + i;
        }
        System.out.println("Summe aller geraden Zahlen: " + b);

        //A3.3.1
        System.out.println("");
        System.out.println("A3.3.1:");
        System.out.println("");

        for (int y = 1; y < 6; y++) {
            for (int x = 1; x < 5; x++) {
                if (y == 1 || y == 5) {
                    if (x == 1 || x == 2 || x == 3) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                } else if (y == 2 || y == 3 || y == 4) {
                    if (x == 1 || x == 4) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
        
        //A3.3.2
        System.out.println("");
        System.out.println("A3.3.2:");
        System.out.println("");

        for (int y = 1; y < 6; y++) {
            for (int x = 1; x < 10; x++) {
                if (y == 1 || y == 5) {
                    if (x == 1 || x == 2 || x == 3 || x >= 6) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                } else if (y == 2 || y == 4) {
                    if (x == 1 || x == 4 || x == 6) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                } else if (y == 3) {
                    if (x == 1 || x == 4 || x >= 6) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }
}
