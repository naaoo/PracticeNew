package com.company;

public class Main {

    public static void main(String[] args) {

        int personsProduced = 4;
        int childrenProducedOvr = 0;

        // Person sets
        Person[] persons = new Person[10];

        Person person0 = new Person();
        persons[0] = person0;
        person0.setFirstName("Inna");
        person0.setLastName("Stepanova");
        person0.setAge(28);
        person0.setGender("female");
        person0.setIsMarried(false);

        Person person1 = new Person();
        persons[1] = person1;
        person1.setFirstName("Aaron");
        person1.setLastName("Dorner");
        person1.setAge(22);
        person1.setGender("male");
        person0.setIsMarried(false);

        Person person2 = new Person();
        persons[2] = person2;
        person2.setFirstName("Simone");
        person2.setLastName("Beireuther");
        person2.setAge(24);
        person2.setGender("female");
        person0.setIsMarried(false);

        Person person3 = new Person();
        persons[3] = person3;
        person3.setFirstName("Manfred");
        person3.setLastName("Bildstein");
        person3.setAge(35);
        person3.setGender("male");
        person0.setIsMarried(false);

        // Marriages usw.

        /*
        Person.printPersonData(person0);
        Person.printPersonData(person1);

        Person.becomePartners(person0, person1);
        Person.printPersonData(person0);
        Person.printPersonData(person1);

        Person.becomeMarried(person0);
        Person.printPersonData(person0);
        Person.printPersonData(person1);

        Person.breakUp(person1);
        Person.printPersonData(person0);
        Person.printPersonData(person1);

        Person.becomeMarried(person0, person3);
        Person.printPersonData(person0);
        Person.printPersonData(person3);

        personsProduced = Person.haveBaby(person0, persons, personsProduced);
        Person.printPersonData(persons[personsProduced - 1]);
        Person.printPersonData(person0);
        Person.printPersonData(person3);

        Person.becomeMarried(person1, person2);
        personsProduced = Person.haveBaby(person1, person2, persons, personsProduced);
        Person.printPersonData(person1);
        Person.printPersonData(person2);
        Person.printPersonData(persons[personsProduced - 1]);

        Person.becomeMarried(persons[4], persons[5]);
        personsProduced = Person.haveBaby(persons[4], persons, personsProduced);
        Person.printPersonData(persons[4]);
        Person.printPersonData(persons[5]);
        Person.printPersonData(persons[personsProduced - 1]);
         */

        //Person.letPersonsAge(persons, personsProduced);

        for (int i = 0; i < personsProduced; i++) {
            Person.printPersonData(persons[i]);
        }

        Person.becomeMarried(person0, person3);
        Person.printPersonData(person0);
        Person.printPersonData(person3);

        personsProduced = Person.haveBaby(person0, persons, personsProduced);
        personsProduced = Person.haveBaby(person0, persons, personsProduced);
        Person.printPersonData(persons[0]);
        //Person.printPersonData(persons[5]);
        person0.loseChild(persons);
        Person.printPersonData(persons[0]);
        Person.printPersonData(persons[3]);

        /*
        Person.breakUp(person0);
        Person.printPersonData(person0);
        Person.printPersonData(person3);

        Person.letPersonsAge(persons, personsProduced);
        Person.printPersonData(person0);
        Person.printPersonData(persons[5]);
         */

        /*
        Person.becomeMarried(persons[personsProduced - 1], person2);
        personsProduced = Person.haveBaby(person2, persons, personsProduced);

        Person.letPersonsAge(persons, personsProduced);

        personsProduced = Person.haveBaby(person2, persons, personsProduced);
        Person.printPersonData(persons[personsProduced - 1]);

        Person.printPersonData(person0);
         */




    }
}
