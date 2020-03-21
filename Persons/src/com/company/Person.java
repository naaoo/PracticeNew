package com.company;

import java.util.Random;
import java.util.Scanner;

public class Person {
    // Constants
    private static final int MIN_AGE = 0;

    // Variables
    String firstName;
    String lastName;
    int age;
    String gender;
    Person partner;
    boolean isMarried;
    String maidenName;
    int childrenProduced = 0;
    Person[] children = new Person[10];

    // Methods
    public static void printPersonData(Person person) {
        System.out.println(person.getFirstName() + " " + person.getLastName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Gender: " + person.getGender());
        if (person.isMarried) {
            System.out.println("Married");
        }
        if (person.partner == null) {
            System.out.println("Single");
        } else {
            System.out.println("Partner: " + person.getPartner());
        }
        if (person.getGender().equals("female") && person.getMaidenName() != null) {
                System.out.println("Maiden name: " + person.getMaidenName());
        }
        for (int i = 0; i < person.children.length; i++) {
            //Print all children
            if (person.children[i] != null) {
                System.out.println("Child " + (i + 1) + ": " + person.children[i]);
            }
        }
        System.out.println();
    }

    // becomePartners
    public static void becomePartners(Person p1, Person p2) {
        p1.setPartner(p2);
        p2.setPartner(p1);
        System.out.println(p1 + " and " + p2 + " are now partners");
        System.out.println();
    }

    // becomeMarried
    public static void becomeMarried(Person p1) {
        Person p2 = p1.getPartner();
        becomeMarried(p1, p2);
    }

    public static void becomeMarried(Person p1, Person p2) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(p1 + " and " + p2 + " are trying to get married");
        if (p1.getAge() < 18 || p2.getAge() < 18) {
            System.out.println("Is this an arranged and forced marriage? (Y / N)");
            String forcedMarriage = scanner.next();
            if (forcedMarriage.toLowerCase().equals("y")) {
                System.out.println("Very well! Continuing marriage process...");
            } else {
                System.out.println("Shame on you!\n");
                return;
            }
        }
        System.out.println(p1 + " and " + p2 + " are marrying now...");
        p1.setIsMarried(true);
        p2.setIsMarried(true);
        p1.setPartner(p2);
        p2.setPartner(p1);
        if (p1.getGender().equals("female")) {
            p1.setMaidenName(p1.getLastName());
            p1.setLastName(p2.getLastName());
        } else if (p2.getGender().equals("female")) {
            p2.setMaidenName(p2.getLastName());
            p2.setLastName(p1.getLastName());
        }
        System.out.println(p1 + " and " + p2 + " are now married");
        System.out.println();
    }

    // becomeDivorced or breakUp
    public static void breakUp(Person p1) {
        Person p2 = p1.getPartner();
        breakUp(p1, p2);
    }
    public static void breakUp(Person p1, Person p2) {
        if (p1.getIsMarried()) {
            System.out.println(p1 + " and " + p2 + " are getting divorced..");
        } else {
            System.out.println(p1 + " and " + p2 + " are breaking up...");
        }
        p1.setIsMarried(false);
        p2.setIsMarried(false);
        p1.setPartner(null);
        p2.setPartner(null);
        if (p1.getMaidenName() != null) {
            p1.setLastName(p1.getMaidenName());
            p1.setMaidenName(null);
        } else if (p2.getMaidenName() != null) {
            p2.setLastName(p2.getMaidenName());
            p2.setMaidenName(null);
        }
        System.out.println(p1 + " and " + p2 + " are now single");
        System.out.println();
    }

    // haveBaby
    public static int haveBaby(Person p1, Person[] persons, int personsProduced) {
        Person p2 = p1.getPartner();
        return haveBaby(p1, p2, persons, personsProduced);
    }
    public static int haveBaby(Person p1, Person p2, Person[] persons, int personsProduced) {
        String[] femaleNames = {"Sandra", "Anna", "Monika", "Kathi", "Veronika"};
        String[] maleNames = {"Klaus", "Franz", "Simon", "Fabio", "Dominik"};
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println(p1 + " and " + p2 + " have started a baby production process...");
        if (p1.getIsMarried() == false || p2.getIsMarried() == false) {
            System.out.println("Unmarried persons can't have babys... Be a good christian...\n");
            return personsProduced;
        } else if (p1.getAge() < 18 || p2.getAge() < 18) {
            System.out.println("God is looking down on you!\nBaby production process aborted\n");
            return personsProduced;
        } else {
            for (int i = personsProduced; i < persons.length; i++) {
                Person child = new Person();
                persons[i] = child;
                persons[i].setLastName(p1.getLastName());
                System.out.println("Gender is being generated");
                int gender = random.nextInt(2);
                if (gender == 0) {
                    persons[i].setGender("female");
                    persons[i].setFirstName(femaleNames[random.nextInt(femaleNames.length)]);
                } else if (gender == 1) {
                    persons[i].setGender("male");
                    persons[i].setFirstName(maleNames[random.nextInt(maleNames.length)]);
                }
                //Manual firstName
                //System.out.println("Gender: " + persons[i].getGender());
                //System.out.println("What's the babys' first name?");
                //persons[i].setFirstName(scanner.nextLine());
                persons[i].setAge(0);
                p1.setChildren(persons, personsProduced);
                p2.setChildren(persons, personsProduced);
                p1.setChildrenProduced(p1.getChildrenProduced() + 1);
                p2.setChildrenProduced(p2.getChildrenProduced() + 1);
                System.out.println("Baby production process finished!");
                System.out.println();
                break;
            }
        }
        personsProduced++;
        return personsProduced;
    }

    public static void letPersonsAge(Person[] persons, int personsProduced) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many years are passing by?");
        int years = scanner.nextInt();
        int i = 0;
        for (int j = 0; j < personsProduced; j++) {
            persons[i].setAge(persons[i].getAge() + years);
            i++;
        }
    }

    // "lose" children
    public void loseChild(Person[] persons) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which child shall be lost? (give me the number...)");
        int child = scanner.nextInt() - 1;
        this.children[child] = null;
        this.getPartner().children[child] = null;
    }

    // give birth statistics
    public static void giveBirthStats(Person[] persons) {
        int females = 0;
        int males = 0;
        for (int i = 0; i < persons.length; i++) {
            if (persons[i] == null) {
                break;
            }
            if (persons[i].getGender().equals("female")) {
                females++;
            } else if (persons[i].getGender().equals("male")) {
                males++;
            }

            // not finished yet

        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= MIN_AGE) {
            this.age = age;
        } else {
            System.out.println("Age can't be lower than 0");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equals("female") || gender.equals("male")) {
            this.gender = gender;
        } else {
            System.out.println("This gender is not known (yet)...");
        }
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public boolean getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }

    public int getChildrenProduced() {
        return childrenProduced;
    }

    public void setChildrenProduced(int childrenProduced) {
        this.childrenProduced = childrenProduced;
    }

    public Person[] getChildren() {
        return children;
    }

    public void setChildren(Person[] persons, int personsProduced) {
        this.children[this.getChildrenProduced()] = persons[personsProduced];
    }
}
