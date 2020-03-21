package com.company;

public class Main {

    public static void main(String[] args) {

        // initialize zoo
        Zoo superZoo = new Zoo("Feldkirch");

        // initialize persons
        Person huber = new Person(superZoo, "Sandra Huber", 46, "female");
        Person mueller = new Person(superZoo, "Andreas Müller", 35, "male");
        Person schneider = new Person(superZoo, "Hans Schneider", 22, "male");
        Person ritter = new Person(superZoo, "Simone Ritter", 26, "female");
        Person stein = new Person(superZoo, "Marina Stein", 21, "female");
        Person sonntag = new Person(superZoo, "Hannes Sonntag", 42, "male");

        superZoo.setHead(huber);

        // initialize enclosures
        Enclosure enclosure0 = new Enclosure(superZoo, EnclosureTypes.glass, 70,5, mueller, false, false);
        Enclosure enclosure1 = new Enclosure(superZoo, EnclosureTypes.fence, 50, 10, schneider, false, false);
        Enclosure enclosure2 = new Enclosure(superZoo, EnclosureTypes.net, 150,50, ritter, false, true);
        Enclosure enclosure3 = new Enclosure(superZoo, EnclosureTypes.fence, 100, 10, stein, true, false);
        Enclosure enclosure4 = new Enclosure(superZoo, EnclosureTypes.glass, 200, 4, sonntag, true, true);


        // initialize animals
        Animal kolibi1 = new Animal(superZoo, enclosure2, "air", "bird", "female", 0.0018, 2);
        Animal kolibi2 = new Animal(superZoo, enclosure2, "air", "bird", "male", 0.0021, 1);
        Animal lion1 = new Animal(superZoo, enclosure4, "land", "lion", "male", 190, 4);
        Animal lion2 = new Animal(superZoo, enclosure4, "land", "lion", "female", 130, 3);
        Animal lion3 = new Animal(superZoo, enclosure4, "land", "lion", "male", 195, 5);
        Animal goat1 = new Animal(superZoo, enclosure1, "land", "goat", "male", 40, 5);
        Animal goat2 = new Animal(superZoo, enclosure1, "land", "goat", "female", 35, 5);
        Animal seal1 = new Animal(superZoo, enclosure3, "water", "seal", "female", 130, 4);
        Animal seal2 = new Animal(superZoo, enclosure3, "water", "seal", "male", 135, 3);
        Animal sheep1 = new Animal(superZoo, enclosure0, "land", "sheep", "male", 50, 3);
        Animal sheep2 = new Animal(superZoo, enclosure0, "land", "sheep", "female", 40, 7);
        Animal sheep3 = new Animal(superZoo, enclosure0, "land", "sheep", "female", 60, 2);
        Animal human1 = new Animal(superZoo, enclosure0, "land", "human", "male", 70, 28);


        // prints, etc.
        superZoo.printZooData();
        superZoo.countAnimalsSpecies("sheep");
        superZoo.countAnimalsGroup("land");
        superZoo.countSpecies();



    }
}
