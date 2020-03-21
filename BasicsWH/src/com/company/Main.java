package com.company;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;



public class Main {

    public static void main(String[] args) {

        //age Verification
        //ageVerification();


        //1000*1000
        int x = 10;
        int y = 10;
        //xTimesY(x, y);


        //Kassenzettel
        //printVoucher();


        //Fahrtenbuch
        //getJourneys();


        //Fahrtenbuch extended (Kennt Dornbirn, Bludenz, Feldkirch)
        //getJourneysExtended();


        //Schleifentest
        //testLoop();


        //Zahlenfolgen (Schleifen)
        //generateNumericalOrder();


        //1000 Zufallszahlen
        //randomNumbers();


        //Wüfeln random
        //randomDiceThrows();


        //Schleife Benutzergesteuert
        //runLoop();


        //Aufgabe If-Anweisungen
        //IfAnweisungen();


        //Aufgabe FeiertagScanner (kennt Neujahr(1.1.), Ostermontag(13.4.), Nationalfeiertag(26.10.)
        //holidayPrint();


        //Aufgabe BMI
        //bmiCalculation();


        //Aufgabe Schaltjahr/Alter
        //1) Schaltjahr
        //isLeapyear();

        //2) Exaktes Alter
        //getAge();


        //Aufgabe Array umdrehen
        //reverseArrayNewArray();
        //reverseArraysameArray();


        //Aufgabe Kassazettel mit Benutzereingabe
        //printVoucherUserEntry();


        //Spiel Zufallszahl erraten
        //randomNumberTipGame();


        //Spiel Wörter erraten
        //hangman();


    }

    private static void hangman() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String[] gameWords = {"Zucchini","Fronleichnam","Portemonnaie","Kernspintomografie","Einfaltspinsel","Burgverlies","Galionsfigur"};


        while (true) {
            String playWord = gameWords[random.nextInt(gameWords.length - 1)];
            String manipWord = playWord;
            int mistakes = 5;

            for (int j = 0; j < 3; j++) {
                for (int i = 1; i < playWord.length(); i++) {
                    int enc = random.nextInt(2);
                    if (enc % 2 == 0) {
                        manipWord = manipWord.substring(0,i) + "*" + manipWord.substring(i + 1);
                    }
                }
            }
            System.out.println("Willkommen zu 'Hangman'!");
            System.out.println("\nHier ist dein Wort:");
            while (mistakes > 0) {
                boolean mistake = true;
                System.out.println(manipWord);
                System.out.println("\nWelchen Buchstaben möchtest du suchen? (" + mistakes + " Fehlversuche übrig)");
                char letter = scanner.next().charAt(0);

                for (int i = 0; i < playWord.length(); i++) {
                    if (playWord.charAt(i) == letter) {
                        manipWord = manipWord.substring(0, i) + letter + manipWord.substring(i + 1);
                        mistake = false;
                    }
                }
                if (mistake) {
                    mistakes--;
                }
                if (checkWin(manipWord)) {
                    break;
                }
            }

            if (checkWin(manipWord)) {
                System.out.println("Du hast gewonnen!");
            } else {
                System.out.println("Du hast verloren! Das Wort hätte " + playWord + " gelautet...");
                System.out.println();
            }
        }
    }

    private static boolean checkWin(String manipWord) {
        boolean noWin = true;
        for (int i = 0; i < manipWord.length(); i++) {
            if (manipWord.charAt(i) == '*') {
                noWin = false;
            }
        }
        return noWin;
    }

    private static void randomNumberTipGame() {
        Random randomNumberGenerator = new Random();
        Scanner playerTipScanner = new Scanner(System.in);

        int randomNumber = randomNumberGenerator.nextInt(50) + 1;
        int playerTip;
        int lastTip = randomNumber;
        int rounds = 10;

        while (true) {
            String feedback = "";
            System.out.println("Zufallszahl (1-50) wurde generiert");
            for (int i = 0; i < rounds; i++) {
                System.out.println("Gib deinen Tipp ab:");
                playerTip = playerTipScanner.nextInt();
                if ((feedback.equals("hoch") && playerTip >= lastTip) || (feedback.equals("niedrig") && playerTip <= lastTip)) {
                    System.out.println("Jetzt habe ich keine Lust mehr...");
                    System.exit(0);
                }
                if (playerTip > randomNumber) {
                    System.out.println("Zu hoch!");
                    feedback = "hoch";
                } else if (playerTip < randomNumber) {
                    System.out.println("Zu niedrig!");
                    feedback = "niedrig";
                } else {
                    System.out.println("Richtig geraten!");
                    break;
                }
                lastTip = playerTip;
            }
        }
    }

    private static void printVoucherUserEntry() {
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        Scanner doubScanner = new Scanner(System.in);


        String[] products = new String[100];
        int[] times = new int[products.length];
        Double[] prices = new Double[products.length];

        System.out.println("Bitte gib die Produktdaten ein:");
        int j = 0;
        while (j < 100) {
            System.out.println("(Noch " + (100 - j) + " Produkte möglich)");
            System.out.println("Produktname:");
            products[j] = stringScanner.nextLine();
            if (products[j].length() > 9) {
                products[j] = products[j].substring(0,10) + ".";
            }
            System.out.println("Anzahl:");
            times[j] = intScanner.nextInt();
            System.out.println("Produktpreis:");
            prices[j] = doubScanner.nextDouble();
            j++;
            System.out.println("Möchtest du noch ein Produkt eingeben? (J / N)");
            String nextItem = stringScanner.nextLine();
            if (nextItem.substring(0,1).toLowerCase().equals("n")) {
                break;
            }

        }

        for (int i = 0; i < j; i++) {
            String secondTab = " x\t";
            if (times[i] < 10) {
                secondTab = secondTab + "\t";
            }
            if (products[i].length() > 7) {
                System.out.println(products[i] + "\t\t" + times[i] + secondTab + prices[i] + "€" );
            } else if (products[i].length() < 4) {
                System.out.println(products[i] + "\t\t\t\t" + times[i] + secondTab + prices[i] + "€" );
            }
            else {
                System.out.println(products[i] + "\t\t\t" + times[i] + secondTab + prices[i] + "€");
            }
        }
        System.out.println("--------------------------------");
        double finalPrice = 0;
        for (int i = 0; i < j; i++) {
            finalPrice = Math.round ((finalPrice + (prices[i]*times[i]))*100)/100.0 ;
        }
        System.out.println("Gesamt\t\t\t\t\t" + finalPrice + "€");
    }

    private static void reverseArraysameArray() {
        int[] array1 = {17,31,49,56,13,21};
        int buffer;

        for (int i = 0; i < array1.length / 2; i++) {
            buffer = array1[i];
            array1[i] = array1[array1.length - 1 - i];
            array1[array1.length - 1 - i] = buffer;
        }
        for (int i = 0; i < array1.length; i++) {
            System.out.println(array1[i]);
        }
    }

    private static void reverseArrayNewArray() {
        int[] array1 = {17,31,49,13,21};
        int[] array2 = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[array1.length - 1 - i];
        }
        for (int i = 0; i < array2.length; i++) {
            System.out.println(array2[i]);
        }
    }

    private static void getAge() {
        Scanner stringScanner = new Scanner(System.in);

        System.out.println("Bitte gib das heutige Datum ein (Format: DD/MM/YYYY):");
        String toDate = stringScanner.nextLine();
        int toDay = Integer.parseInt(toDate.substring(0,2));
        int toMonth = Integer.parseInt(toDate.substring(3,5));
        int toYear = Integer.parseInt(toDate.substring(6));

        System.out.println("Bitte gib dein Geburtsdatum ein (Format: DD/MM/YYYY):");
        String birthDate = stringScanner.nextLine();
        int birthDay = Integer.parseInt(birthDate.substring(0,2));
        int birthMonth = Integer.parseInt(birthDate.substring(3,5));
        int birthYear = Integer.parseInt(birthDate.substring(6));

        //Alter in Jahren
        int ageInYears = toYear - birthYear;
        if (toMonth < birthMonth || (toMonth == birthMonth && toDay < birthMonth)) {
            ageInYears = ageInYears - 1;
        }
        System.out.println("Du bist " + ageInYears + " Jahre alt");

        //Alter in Monaten
        int ageInMonths = 0;
        ageInMonths = ageInMonths + 12 - birthMonth;
        for (int i = birthYear + 1; i < toYear; i++) {
            ageInMonths = ageInMonths + 12;
        }
        ageInMonths = ageInMonths + toMonth;
        if (toDay < birthMonth) {
            ageInMonths = ageInMonths - 1;
        }
        System.out.println("Du bist " + ageInMonths + " Monate alt");

        //Alter in Tagen
        int ageInDays = 0;
        //ersten Monat fertigrechnen
        ageInDays = ageInDays + getDaysInMonth(birthMonth, birthYear) - birthDay;
        //erstes Jahr fertigrechnen
        for (int i = birthMonth + 1; i < 13; i++) {
            ageInDays = ageInDays + getDaysInMonth(i, birthYear);
        }
        //Jahre rechnen (außer letztes)
        for (int i = birthYear + 1; i < toYear; i++) {
            for (int j = 1; j < 13; j++) {
                ageInDays = ageInDays + getDaysInMonth(j, i);
            }
        }
        //Monate letztes Jahr rechnen
        for (int i = 1; i < toMonth; i++) {
            ageInDays = ageInDays + getDaysInMonth(i, toYear);
        }
        //letzen Monat fertig rechnen
        ageInDays = ageInDays + toDay;

        //Alter in Wochen
        int ageInWeeks;
        ageInWeeks = ageInDays / 7;
        System.out.println("Du bist " + ageInWeeks + " Wochen alt");
        System.out.println("Du bist " + ageInDays + " Tage alt");
    }

    private static int getDaysInMonth(int month, int year) {
        int daysInMonth = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: daysInMonth = 31; break;
            case 4: case 6: case 9: case 11: daysInMonth = 30; break;
            case 2:
                if (year % 4 == 0) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
        }
        return daysInMonth;
    }

    private static void isLeapyear() {
        Scanner intScanner = new Scanner(System.in);
        boolean leapyear = false;

        while (true) {
            System.out.println("Bitte gib dein Geburtsjahr ein (Format: JJJJ):");
            int birthYear = intScanner.nextInt();
            if (birthYear % 4 == 0) {
                leapyear = true;
            }
            if (leapyear) {
                System.out.println(birthYear + " ist ein Schaltjahr");
            } else {
                System.out.println(birthYear + " ist kein Schaltjahr");
            }
        }
    }

    private static void bmiCalculation() {
        Scanner intScanner = new Scanner(System.in);
        Scanner doubScanner = new Scanner(System.in);

        System.out.println("Bitte gib dein Alter ein (in Jahren):");
        int age = intScanner.nextInt();
        System.out.println("Bitte gib deine Körpergröße ein (in m, Format: X,XX:");
        double hight = doubScanner.nextDouble();
        System.out.println("Bitte gib dein Gewicht ein (in kg):");
        double weight = doubScanner.nextDouble();

        double bmi = weight / Math.pow(hight, 2.0);

        boolean normalWeight = isNormalWeight(age, bmi);

        System.out.println("BMI: " + (Math.round(bmi * 10) / 10.0));
        if (normalWeight) {
            System.out.println("Du befindest dich im altersentsprechenden Normalbereich");
        } else {
            System.out.println("Du befindest dich nicht im altersentsprechenden Normalbereich");
        }
    }

    private static boolean isNormalWeight(int age, double bmi) {
        boolean normalWeight = false;
        if (age >= 19 && age <= 24) {
            if (bmi >= 19 && bmi <= 24) {
                normalWeight = true;
            }
        } else if (age >= 25 && age <= 34) {
            if (bmi >= 20 && bmi <= 25) {
                normalWeight = true;
            }
        } else if (age >= 35 && age <= 44) {
            if (bmi >= 21 && bmi <= 26) {
                normalWeight = true;
            }
        } else if (age >= 45 && age <= 54) {
            if (bmi >= 22 && bmi <= 27) {
                normalWeight = true;
            }
        } else if (age >= 55 && age <= 64) {
            if (bmi >= 23 && bmi <= 28) {
                normalWeight = true;
            }
        } else if (age > 64) {
            if (bmi >= 24 && bmi <= 29) {
                normalWeight = true;
            }
        }
        return normalWeight;
    }

    private static void holidayPrint() {
        Scanner stringScanner = new Scanner(System.in);
        String holiday = "", date = "", weekday = "";

        System.out.println("Welchen Feiertag möchtest du ausgeben?");
        String input = stringScanner.nextLine();
        String holidaySwitch = input.substring(0,2).toLowerCase();
        switch (holidaySwitch) {
            case "ne" : holiday = "Neujahr"; date = "1.1.2020"; weekday = "Mittwoch"; break;
            case "os" : holiday = "Ostermontag"; date = "13.4.2020"; weekday = "Montag"; break;
            case "na" : holiday = "Nationalfeiertag"; date = "26.10.2020"; weekday = "Montag"; break;
            default: System.out.println("Kein bekannter Wochentag"); break;
        }
        System.out.println(holiday + ": " + weekday + ", " + date);
    }

    private static void IfAnweisungen() {
        //1
        boolean kannGrundstückKaufen;
        double grundstückspreis = 10D;
        double eigenmittel = 2D;
        double verwandterLeihtGeld = 7D;
        if (eigenmittel >= (grundstückspreis*0.2) || verwandterLeihtGeld >= (grundstückspreis - eigenmittel)) {
            kannGrundstückKaufen = true;
        } else {
            kannGrundstückKaufen = false;
        }
        System.out.println("Ich kann das Grundstück kaufen: " + kannGrundstückKaufen);

        //2
        double einkauf = 500D;
        double rabatt;
        if (einkauf >= 200 && einkauf < 400) {
            rabatt = 0.03D;
        } else if (einkauf >= 400 && einkauf <= 600){
            rabatt = 0.05D;
        } else {
            rabatt = 0D;
        }
        System.out.println("Rabatt: " + rabatt*100 + "%");
        System.out.println("Der Endpreis beträgt: " + (einkauf * (1 - rabatt)) + "€");

        //3
        int alter = 30;
        boolean fahrradführerschein = false;
        boolean mopedführerschein = false;
        boolean autoführerschein = true;

        boolean darfFahrradAlleine, darfMoped, darfAuto;

        if (alter >= 10 && fahrradführerschein) {
            darfFahrradAlleine = true;
        } else {
            darfFahrradAlleine = false;
        }
        if (alter >= 15 && mopedführerschein) {
            darfMoped = true;
        } else {
            darfMoped = false;
        }
        if (alter >= 18 && autoführerschein && alter < 65) {
            darfAuto = true;
        } else {
            darfAuto = false;
        }
        System.out.println("Ich darf ohne Eltern Fahrradfahren: " + darfFahrradAlleine);
        System.out.println("Ich darf Mopedfahren: " + darfMoped);
        System.out.println("Ich darf Autofahren: " + darfAuto);
    }

    private static void runLoop() {
        Scanner intScanner = new Scanner(System.in);

        System.out.println("Bitte gib den Startwert der Schleife ein:");
        int start = intScanner.nextInt();
        System.out.println("Bitte gib die Schrittweite der Schleife ein:");
        int increment = intScanner.nextInt();
        System.out.println("Bitte gib den Endwert der Schleife ein:");
        int end = intScanner.nextInt();

        for (int i = start; i < (end + 1); i = i + increment) {
            System.out.println(i);
        }
    }

    private static void randomDiceThrows() {
        Random randomThrowGenerator = new Random();
        int randomThrow = 0;
        int i = 0;
        int six = 0;

        while (six != 2) {
            i++;
            randomThrow = randomThrowGenerator.nextInt(6) + 1;
            System.out.println("Wurf " + i + " : " + randomThrow);
            if (randomThrow == 6) {
                six++;
            } else {
                six = 0;
            }

        }
        System.out.println("Es musste " + i + "mal gewürfelt werden um 2 aufeinanderfolgende 6en zu würfeln");
    }

    private static void randomNumbers() {
        Random randomNumberGenerator = new Random();
        int[] randomNumbers = new int[1000];
        int[] counter = new int[10];
        for (int i = 0; i < 1000; i++) {
            int randomNumber = randomNumberGenerator.nextInt(10) + 1;
            randomNumbers[i] = randomNumber;
        }
        for (int i = 0; i < randomNumbers.length; i++) {
            int number = randomNumbers[i];
            for (int j = 0; j < counter.length; j++) {
                if (number == (j + 1)) {
                    counter[j]++;
                }
            }
        }
        for (int i = 0; i < counter.length; i++) {
            System.out.println((i + 1) + " kam " + counter[i] + " mal vor");
        }
        int max = 0;
        int mostCommonNumber = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > max) {
                max = counter[i];
                mostCommonNumber = i;
            }
        }
        System.out.println((mostCommonNumber + 1) + " war die häufigste Zahl (" + max + "mal)");
    }

    private static void generateNumericalOrder() {
        int a = -5;
        while (a < 20) {
            System.out.print(a + " ");
            a = a + 3;
        }
        System.out.println();

        int b = 1;
        while (b < 4097) {
            System.out.print(b + " ");
            b = b * 2;
        }
        System.out.println();

        int c = 3;
        while (c < 4099) {
            System.out.print(c + " ");
            c = (c - 1) * 2;
        }
        System.out.println();

        int d = 1;
        int e = 0;
        while (d < 93) {
            System.out.print(d + " ");
            d = d + e + 1;
            e = e + 1;
        }
        System.out.println();
    }

    private static void testLoop() {
        int zaehler = 0;
        int n = 13;
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            zaehler = zaehler + 1;
        }
        System.out.println("Ergebnis: " + zaehler);
    }

    private static void getJourneysExtended() {
        Scanner dateScanner = new Scanner(System.in);
        Scanner startScanner = new Scanner(System.in);
        Scanner endScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);

        String[] journeys = new String[100];

        boolean newJourney = true;
        String date, start, end;
        int distance = 0;

        int i = 0;
        while (newJourney) {
            System.out.println("An welchem Tag bist du gefahren? (Format: DD.MM.YYYY)");
            date = dateScanner.nextLine();
            System.out.println("Wo bist du gestartet?");
            start = startScanner.nextLine();
            System.out.println("Wohin bist du gefahren?");
            end = endScanner.nextLine();
            switch (start) {
                case "Bludenz":
                    switch (end) {
                        case "Feldkirch": distance = 10; break;
                        case "Dornbirn": distance = 50; break;
                    } break;
                case "Feldkirch":
                    switch (end) {
                        case "Bludenz": distance = 10; break;
                        case "Dornbirn": distance = 40; break;
                    } break;
                case "Dornbirn":
                    switch (end) {
                        case "Bludenz": distance = 50; break;
                        case "Feldkirch": distance = 40; break;
                    } break;
            }
            journeys[i] = (date + " " + start + " - " + end + " (" + distance + "km)");
            i++;
            System.out.println("Möchtest du eine neue Fahrt eingeben? (J/N)");
            String nextJourney = stringScanner.next();
            if (nextJourney.substring(0, 1).toUpperCase().equals("N")) {
                newJourney = false;
            }
        }
        System.out.println("Fertiges Fahrtenbuch für diesen Monat:");
        for (int j = 0; j < journeys.length; j++) {
            if (journeys[j] == null) {
                break;
            }
            System.out.println(journeys[j]);
        }
    }

    private static void getJourneys() {
        Scanner dateScanner = new Scanner(System.in);
        Scanner startScanner = new Scanner(System.in);
        Scanner endScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        String[] journeys = new String[100];
        boolean newJourney = true;
        String date, start, end;

        int i = 0;
        while (newJourney) {
            System.out.println("An welchem Tag bist du gefahren? (Format: DD.MM.YYYY)");
            date = dateScanner.nextLine();
            System.out.println("Wo bist du gestartet?");
            start = startScanner.nextLine();
            System.out.println("Wohin bist du gefahren?");
            end = endScanner.nextLine();
            journeys[i] = (date + " " + start + " - " + end);
            i++;
            System.out.println("Möchtest du eine neue Fahrt eingeben? (J/N)");
            String nextJourney = stringScanner.next();
            if (nextJourney.substring(0, 1).toUpperCase().equals("N")) {
                newJourney = false;
            }
        }
        System.out.println("Fertiges Fahrtenbuch für diesen Monat:");
        for (int j = 0; j < journeys.length; j++) {
            if (journeys[j] == null) {
                break;
            }
            System.out.println(journeys[j]);
        }
    }

    private static void printVoucher() {
        Random randomNumber = new Random();
        String[] products = {"Shampoo","Birnen", "Zahnpasta", "Brot"};
        int[] times = {7,1,9,1};
        Double[] prices = new Double[products.length];
        for (int i = 0; i < products.length; i++) {
            prices[i] = Math.round (0 + (25 - 0) * randomNumber.nextDouble()*100)/100.0;
        }
        for (int i = 0; i < products.length; i++) {
            if (products[i].length() > 7) {
                System.out.println(products[i] + "\t" + times[i] + " x\t\t" + prices[i] + "€" );
            } else {
                System.out.println(products[i] + "\t\t" + times[i] + " x\t\t" + prices[i] + "€");
            }
        }
        System.out.println("---------------------------");
        double finalPrice = 0;
        for (int i = 0; i < products.length; i++) {
            finalPrice = Math.round ((finalPrice + (prices[i]*times[i]))*100)/100.0 ;
        }
        System.out.println("Gesamt\t\t\t\t" + finalPrice + "€");
    }

    private static void xTimesY(int x, int y) {
        for (int i = 1; i < (x + 1); i++) {
            for (int j = 1; j < (y + 1) ; j++) {
                System.out.println(i + " mal " + j + " = " + (i * j));
            }
        }
    }

    private static void ageVerification() {
        Scanner intScanner = new Scanner(System.in);

        System.out.println("Hallo! Bitte gib dein Alter ein:");
        int age = intScanner.nextInt();

        if (age >= 18) {
            System.out.println("Viel Spaß!");
        } else {
            System.out.println("Versuch es in " + (18-age) + " Jahren wieder");
        }
    }
}
