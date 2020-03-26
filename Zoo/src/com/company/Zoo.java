package com.company;

import java.util.ArrayList;

public class Zoo {
    Person head;
    String location;
    ArrayList<Enclosure> enclosureArr = new ArrayList<>();
    ArrayList<Person> employeeArr = new ArrayList<>();

    public Zoo(String location) {
        this.location = location;
    }

    // methods
    //Print zoo data
    public void printZooData() {
        System.out.println("Zoo: " + this.location);
        System.out.println("Head: " + this.head);
        System.out.println("Animals (Overall): " + countAnimals());
        System.out.println("Enclosures: " + this.enclosureArr.size());
        System.out.println("Employees: " + this.employeeArr.size());
        System.out.println();
    }
    public int countAnimals() {
        int count = 0;
        for (Enclosure enclosure : enclosureArr) {
            count += enclosure.animalsArr.size();
        }
        return count;
    }

    public void countAnimalsSpecies(String species) {
        int counterOvr = 0;
        System.out.println("Animal count: species " + species);
        for (Enclosure enclosure : enclosureArr) {
            int counterEnc = 0;
            for (Animal animal : enclosure.animalsArr) {
                if (animal.species.equalsIgnoreCase(species)) {
                    counterEnc++;
                    counterOvr++;

                }
            }
            System.out.println("\t" + enclosure + ": " + counterEnc);
        }
        System.out.println(species + "s overall: " + counterOvr + "\n");
    }

    public void countAnimalsGroup(String group) {
        int counterOvr = 0;
        System.out.println("Animal count: group " + group + " animals");
        for (Enclosure enclosure : enclosureArr) {
            int counterEnc = 0;
            for (Animal animal : enclosure.animalsArr) {
                if (animal.group.equalsIgnoreCase(group)) {
                    counterEnc++;
                    counterOvr++;
                }
            }
            System.out.println("\t" + enclosure + ": " + counterEnc);
        }
        System.out.println(group + " animals overall: " + counterOvr + "\n");
    }

    // count species
    public void countSpecies() {
        ArrayList<String> speciesArr = new ArrayList<>();
        // Loop enclosures
        for (Enclosure enclosure : this.enclosureArr) {
            // Loop animals
            for (Animal animal : enclosure.animalsArr) {
                // get species
                String species = animal.species;
                // set first null to species
                if (speciesArr.size() == 0) {
                    speciesArr.add(species);
                    break;
                }
                //check if species already in array
                if (!speciesArr.contains(species)) {
                    speciesArr.add(species);
                }
            }
        }
        System.out.println("Amount of different species in zoo: " + speciesArr.size());
    }

    public void setHead(Person head) {
        this.head = head;
    }
}
