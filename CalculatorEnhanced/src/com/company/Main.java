package com.company;

public class Main {

    public static void main(String[] args) {

        String input = "(((9*4)*5) *8) + (16/7) - 4";             //z.B... ((8/4) *2) + (16/8) * 2
        //System.out.println(input); //TEst

        String workString = input.replaceAll(" ", "");
        System.out.println(workString); //Test

        boolean bracketsExist = true;
        while (bracketsExist) {

            String partString = getPartStringWithBrackets(workString);

            String partResult = partCalculation(partString);
            //System.out.println(partResult); //Test

            workString = replaceWorkString(workString, partString, partResult);
            System.out.println(workString); //TEst
            if (workString.indexOf('(') < 0 || workString.indexOf(')') < 0) {
                bracketsExist = false;
            }
        }

        boolean dotOperatorsExist = true;
        if (workString.indexOf('*') < 0 && workString.indexOf('/') < 0) {
            dotOperatorsExist = false;
        }
        while (dotOperatorsExist) {

            String partString = getPartStringAfterBrackets(workString);
            //System.out.println(partString); //Test

            String partResult = partCalculation(partString);
            //System.out.println(partResult); //Test

            workString = replaceWorkStringAfterBrackets(workString, partString, partResult);
            System.out.println(workString); //Test

            if (workString.indexOf('*') < 0 || workString.indexOf('/') < 0) {
                dotOperatorsExist = false;
            }
        }
        boolean OperatorsExist = true;
        if (workString.indexOf('*') < 0 && workString.indexOf('/') < 0 && workString.indexOf('+') < 0 && workString.indexOf('-') < 0) {
            OperatorsExist = false;
        }
        while (OperatorsExist) {
            String partResult = calculateEndResult(workString);
            System.out.println("End result = " + partResult);
            if (workString.indexOf('*') < 0 || workString.indexOf('/') < 0 || workString.indexOf('+') < 0 || workString.indexOf('-') < 0) {
                OperatorsExist = false;
            }
        }
    }

    private static String calculateEndResult(String workString) {
        String partResult ="";

            String partString =getPartStringAfterDots(workString);
            //System.out.println(partString); //Test

            partResult = partCalculation(partString);
            //System.out.println(partResult);

        return partResult;
    }

    private static String getPartStringAfterDots(String workString) {
        String partString = "";
        int substringEnd = 0;

        for (int i = 0; i < workString.length(); i++) {
            if (workString.charAt(i) == '+' || workString.charAt(i) == '-' || workString.charAt(i) == '*' || workString.charAt(i) == '/') {
                for (int j = i + 1; j < workString.length(); j++) {
                    if (workString.charAt(j) == '+' || workString.charAt(j) == '-' || workString.charAt(j) == '*' || workString.charAt(j) == '/') {
                        substringEnd = j - 1;
                        break;
                    }
                    substringEnd = j - 1;
                }
                break;
            }
        }
        partString = workString.substring(0, substringEnd + 1);
        return partString;
    }

    private static String getPartStringAfterBrackets(String workString) {
        String partString = "";
        int substringStart = 0;
        int substringEnd = 0;

        for (int i = 0; i < workString.length(); i++) {
            if (workString.charAt(i) == '*' || workString.charAt(i) == '/') {
                for (int j = i; true; j--) {
                    if (workString.charAt(j) == '+' || workString.charAt(j) == '-') {
                        substringStart = j + 1;
                        break;
                    }
                }
                for (int j = i + 1; j < workString.length(); j++) {
                    if (workString.charAt(j) == '+' || workString.charAt(j) == '-' || workString.charAt(j) == '*' || workString.charAt(j) == '/') {
                        substringEnd = j;
                        break;
                    }
                    substringEnd = j;
                }
            }
            partString = workString.substring(substringStart, substringEnd + 1);
        }
        return partString;
    }

    private static String replaceWorkString(String workString, String partString, String partResult) {
        //Methode muss Ergebnis aus partCalculation (partResult) in das Array einfügen (an Stelle von partString)
        workString = workString.replace(("(" + partString + ")"), partResult);
        return workString;
    }
    private static String replaceWorkStringAfterBrackets(String workString, String partString, String partResult) {
        //Methode muss Ergebnis aus partCalculation (partResult) in das Array einfügen (an Stelle von partString)
        workString = workString.replace(partString, partResult);
        return workString;
    }

    private static String getPartStringWithBrackets(String workString) {
        //MEthode sucht sich aus String/Char immer eine Klammer heraus, macht partString daraus --> sendet an partCalculation
        // for stringLength (if Klammer ( dann nimm alles bis zur nächsten Klammer ) und mache substring daraus...
        String partString = "";
        int substringLength = 0;
        for (int i = 0; i < workString.length(); i++) {
            if (workString.charAt(i) == '(') {
                if (workString.charAt(i + 1) == '(') {
                    continue;
                } else {
                    for (int j = i; j < workString.length(); j++) {
                        if (workString.charAt(j) == ')') {
                            substringLength = j;
                            break;
                        }
                    }
                }
                partString = workString.substring(i + 1, substringLength);
                break;
            }
        }
        //System.out.println(partString); //Test
        return partString;
    }

    //Methode rechnen gelernt, erhält String (partString), returned partResult als String
    private static String partCalculation(String partString) {
        //zweistellige Zahlen: Wert Länge wird durch test (bis zum Operator) festgelegt
        int double1Length = 0;
        int double2Length = 0;
        // double1Length richtig setzen (num1 startet immer auf 0)
        for (int i = 0; i < partString.length(); i++) {
            if (partString.charAt(i) == '*' || partString.charAt(i) == '+' || partString.charAt(i) == '-' || partString.charAt(i) == '/') {
                double1Length = i;
                break;
            }
        }
        // double2Length richtig setzen (num2 startet immer auf 1 + double1Length)
        for (int i = double1Length; i < partString.length(); i++) {
            if (partString.charAt(i) == '*' || partString.charAt(i) == '+' || partString.charAt(i) == '-' || partString.charAt(i) == '/') {
                double2Length = i;
                break;
            }
        }

        double num1 = Double.parseDouble(partString.substring(0, double1Length));
        double num2 = Double.parseDouble(partString.substring(double1Length + 1));
        char operator = partString.charAt(double1Length);
        double result = 0.0D;
        String resultString = "";
        //System.out.println(num1); //Test
        //System.out.println(num2); //Test


        if (operator == '+') {
            result = num1 + num2;
        } else if (operator == '-'){
            result = num1 - num2;
        } else if (operator == '*') {
            result = num1 * num2;
        } else if (operator == '/') {
            result = num1 / num2;
        }
        resultString = Double.toString(result);
        return resultString;
    }
}
